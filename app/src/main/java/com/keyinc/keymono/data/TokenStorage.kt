package com.keyinc.keymono.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Singleton

@Singleton
class TokenStorage(context: Context)  {

    private val masterKey: MasterKey =
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()


    private val encryptedSharedPreferences =
        EncryptedSharedPreferences.create(
            context, "secret_shared_prefs", masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun deleteToken() {
        encryptedSharedPreferences.edit().remove("token").apply()
    }

    fun getToken(): String {
        return encryptedSharedPreferences.getString("token", "")
            ?: ""
    }

    fun saveToken(token: String) {
        encryptedSharedPreferences.edit().putString("token", token).apply()
    }

}