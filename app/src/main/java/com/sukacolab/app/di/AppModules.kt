package com.sukacolab.app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sukacolab.app.data.repository.*
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.domain.repository.AuthRepository
import com.sukacolab.app.domain.usecase.LoginUseCase
import com.sukacolab.app.ui.feature.login.LoginViewModel
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.profile_edit.ProfileEditViewModel
import com.sukacolab.app.ui.feature.register.RegisterViewModel
import com.sukacolab.app.ui.feature.splash.SplashViewModel
import com.sukacolab.app.ui.feature.user.home.HomeViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.about.EditAboutViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.certification.CertificationViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.certification.add_certification.AddCertificationViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.certification.edit_certification.EditCertificationViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.education.EducationViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.education.add_education.AddEducationViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.education.edit_education.EditEducationScreen
import com.sukacolab.app.ui.feature.user.profile.sub_screen.education.edit_education.EditEducationViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.ExperienceViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.add_experience.AddExperienceViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.edit_experience.EditExperienceViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.email.SettingEmailViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.password.SettingPasswordViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.SkillViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.add_skill.AddSkillViewModel
import com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.edit_skill.EditSkillViewModel
import com.sukacolab.app.util.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val loggingInterceptor =
            HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(100, TimeUnit.SECONDS) // Set the connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // Set the read timeout
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val viewModelModules = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { ProfileViewModel(get(),get()) }
    viewModel { ExperienceViewModel(get(), get(), get()) }
    viewModel { CertificationViewModel(get(), get(), get()) }
    viewModel { SkillViewModel(get(), get(), get()) }
    viewModel { EducationViewModel(get(), get(), get()) }
    viewModel { SettingEmailViewModel(get(), get(), get()) }
    viewModel { SettingPasswordViewModel(get(), get(), get()) }
    viewModel { EditAboutViewModel(get(), get(), get()) }
    viewModel { AddExperienceViewModel(get(), get(), get()) }
    viewModel { EditExperienceViewModel(get(), get(), get()) }
    viewModel { AddCertificationViewModel(get(), get(), get()) }
    viewModel { EditCertificationViewModel(get(), get(), get()) }
    viewModel { AddSkillViewModel(get(), get(), get()) }
    viewModel { EditSkillViewModel(get(), get(), get()) }
    viewModel { AddEducationViewModel(get(), get(), get()) }
    viewModel { EditEducationViewModel(get(), get(), get()) }
    viewModel { ProfileEditViewModel(get(),get()) }
}

val useCaseModule = module {
    single { LoginUseCase(get()) }
    single<AuthRepository> { DefaultAuthRepository() }
    single { ProfileRepository(get(),get()) }
    single { ResourcesProvider(androidContext()) }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_key")

val dataPreferencesModule = module {

    single {
        AuthPreferences( dataStore = androidContext().dataStore)
    }
}

class ResourcesProvider(private val context: Context) {
    fun getString(resId: Int): String {
        return context.getString(resId)
    }
}

