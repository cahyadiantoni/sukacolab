package com.sukacolab.app.ui.feature.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.ui.feature.profile.uiState.ExperienceUiState
import com.sukacolab.app.ui.feature.profile.uiState.ProfileUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authPreferences: AuthPreferences,
    private val profileRepo: ProfileRepository,
) : ViewModel() {
    val response: MutableState<ProfileUiState> = mutableStateOf(ProfileUiState.Empty)
    val responseExperience: MutableState<ExperienceUiState> = mutableStateOf(ExperienceUiState.Empty)

    val id: Int?
        get() = (response.value as? ProfileUiState.Success)?.data?.id
    val name: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.name
    val email: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.email
    val photo: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.photo
    val summary: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.summary
    val linkedin: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.linkedin
    val github: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.github
    val whatsapp: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.whatsapp
    val instagram: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.instagram
    val resume: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.resume
    val about: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.about

    init {
        profileDetails()
        getExperience()
    }

    private fun profileDetails() = viewModelScope.launch {
        profileRepo.profileDetails()
            .onStart {
                response.value = ProfileUiState.Loading
            }.catch {
                response.value = ProfileUiState.Failure(it)
            }.collect {
                response.value = ProfileUiState.Success(it)
            }
    }

    fun getExperience() = viewModelScope.launch {
        profileRepo.getExperience()
            .onStart {
                responseExperience.value = ExperienceUiState.Loading
            }.catch {
                responseExperience.value = ExperienceUiState.Failure(it)
            }.collect {
                responseExperience.value = ExperienceUiState.Success(it)
            }
    }

    fun logout() {
       viewModelScope.launch {
           authPreferences.clearAuthToken()
       }
    }

}