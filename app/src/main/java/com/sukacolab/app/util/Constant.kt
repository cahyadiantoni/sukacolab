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
    const val ALERT_HOMESCREEN = "Mohon maaf untuk saat ini fitur yang anda pilih masih dalam tahap pengembangan. Namun anda dapat mencoba fitur ini dengan dengan browser anda."
    const val ABOUT_APP = "Aplikasi ini adalah produk dari capstone project yang dikerjakan untuk program Bangkit Academy. Projek mulai dikerjakan pada tanggal 13 April 2023 oleh 6 orang developer:"
    const val ABOUT_ONE = "Andre Christoga Pramaditya, Universitas Indonesia\nSoftware Engineer"
    const val ABOUT_TWO = "Irfan Maulana, Universitas Singaperbangsa Karawang\nAndroid Developer"
    const val ABOUT_THREE = "Ryo Martin Sopian, Universitas Bandar Lampung\nAndroid Developer"
    const val ABOUT_FOUR = "Safrizal Ardana Ardiyansa, Universitas Brawijaya\nMachine Learning Engineer"
    const val ABOUT_FIVE = "Yischard Meynardi Borean, Politeknik Negeri Ujung Pandang\nTeam Lead & Cloud Engineer"
    const val ABOUT_SIX = "Yusuf Wibisono, Univesitas Bina Nusantara\nProduct Manager & Backend Engineer"

    //Pictures
    const val IMAGE_BCA = "https://marcopolis.net/images/stories/indonesia-report/2016/companies/Bank_Central_Asia.jpg"
    const val IMAGE_MANDIRI = "https://assets.weforum.org/organization/image/responsive_small_webp_NsSr9Pm0VMIbvt2UpLt0qMal8KKBy1MrjG2U5IgTxVQ.webp"
    const val IMAGE_BRI = "https://companiesmarketcap.com/img/company-logos/256/BBRI.JK.webp"
    const val IMAGE_MATRIX = "https://media.wired.com/photos/5ca648a330f00e47fd82ae77/master/w_2560%2Cc_limit/Culture_Matrix_Code_corridor.jpg"
}