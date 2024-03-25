package com.sukacolab.app.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constant {
    const val BASE_URL = "https://api.sukacolab.com"
    const val AUTH_PREFERENCES = "AUTH_PREF"
    val ONBOARDING_KEY = booleanPreferencesKey("ONBOARDING_KEY")
    val AUTH_KEY = stringSetPreferencesKey("auth_key")
    val AUTH_ID = stringSetPreferencesKey("auth_id")

    //Alert
    const val ALERT_HOMESCREEN = "Mohon maaf untuk saat ini fitur yang anda pilih masih dalam tahap pengembangan. Nantikan update yang akan datang!"
    const val ALERT_PROJECT_REVIEW = "Untuk project paid (digaji), akan ada review oleh admin. Hubungi admin jika memerlukan bantuan!"
    const val ABOUT_APP = "Sukacolab merupakan kepanjangan dari Sukacode Collaboration yang merupakan apilkasi untuk kolaborasi proyek. Pada aplikasi ini anda dapat bergabung ke proyek dan mencari rekan."
    const val ABOUT_ONE = "Dibuat oleh Cahya Diantoni"
    const val ABOUT_TWO = "PT. Sukacode Solusi Teknologi"
}