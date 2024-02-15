package com.keyinc.keymono.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Singleton

@Singleton
class TokenStorage(context: Context)  {

    private companion object {
        const val TOKEN_REFERENCE = "secret_shared_prefs"
        const val SHARED_KEY = "token"
    }


    private val masterKey: MasterKey =
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()


    private val encryptedSharedPreferences =
        EncryptedSharedPreferences.create(
            context, TOKEN_REFERENCE, masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun deleteToken() {
        encryptedSharedPreferences.edit().remove(SHARED_KEY).apply()
    }

    fun getToken(): String {
        return encryptedSharedPreferences.getString(SHARED_KEY, "")
            ?: ""
    }

    fun saveToken(token: String) {
        encryptedSharedPreferences.edit().putString(SHARED_KEY, token).apply()
    }

}