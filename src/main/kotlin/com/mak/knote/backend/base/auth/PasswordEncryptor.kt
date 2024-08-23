package com.mak.knote.backend.base.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

@OptIn(ExperimentalStdlibApi::class)
internal class PasswordEncryptor : IPasswordEncryptor {

    private val ALGORITHM = "PBKDF2WithHmacSHA512"
    private val ITERATIONS = 120_000
    private val KEY_LENGTH = 256
    private val SECRET = "SomeRandomSecret"
    override suspend fun generateHash(password: String): String = withContext(Dispatchers.IO) {
        val salt = generateRandomSalt()
        val hash = createHashWith(salt, password)
        return@withContext hash
    }

    private fun createHashWith(salt: String, password: String): String {
        val combinedSalt = "$salt$SECRET".toByteArray()
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), combinedSalt, ITERATIONS, KEY_LENGTH)
        val key: SecretKey = factory.generateSecret(spec)
        val hash: ByteArray = key.encoded
        return "${hash.toHexString()}.$salt"
    }

    override suspend fun validatePassword(saltedHash: String, password: String): Boolean = withContext(Dispatchers.IO) {
        val (_, salt) = saltedHash.split('.')
        val enteredPassword = createHashWith(salt, password)
        return@withContext enteredPassword == saltedHash
    }

    private fun generateRandomSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt.toHexString()
    }

//    private fun ByteArray.toHexString(): String =
//        HexFormat.of().formatHex(this)
}

interface IPasswordEncryptor {
    suspend fun generateHash(password: String): String

    suspend fun validatePassword(saltedHash: String, password: String): Boolean
}