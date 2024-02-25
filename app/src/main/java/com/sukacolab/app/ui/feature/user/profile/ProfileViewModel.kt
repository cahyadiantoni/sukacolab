package com.sukacolab.app.ui.feature.user.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.ui.feature.user.profile.ui_state.CertificationUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.EducationUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.ExperienceUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.ProfileUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.SkillUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authPreferences: AuthPreferences,
    private val profileRepo: ProfileRepository,
) : ViewModel() {
    val response: MutableState<ProfileUiState> = mutableStateOf(ProfileUiState.Empty)
    val responseExperience: MutableState<ExperienceUiState> = mutableStateOf(ExperienceUiState.Empty)
    val responseCertification: MutableState<CertificationUiState> = mutableStateOf(
        CertificationUiState.Empty)
    val responseSkill: MutableState<SkillUiState> = mutableStateOf(SkillUiState.Empty)
    val responseEducation: MutableState<EducationUiState> = mutableStateOf(EducationUiState.Empty)

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
        getCertification()
        getSkill()
        getEducation()
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

    private fun getExperience() = viewModelScope.launch {
        profileRepo.getExperience()
            .onStart {
                responseExperience.value = ExperienceUiState.Loading
            }.catch {
                responseExperience.value = ExperienceUiState.Failure(it)
            }.collect {
                responseExperience.value = ExperienceUiState.Success(it)
            }
    }

    private fun getCertification() = viewModelScope.launch {
        profileRepo.getCertification()
            .onStart {
                responseCertification.value = CertificationUiState.Loading
            }.catch {
                responseCertification.value = CertificationUiState.Failure(it)
            }.collect {
                responseCertification.value = CertificationUiState.Success(it)
            }
    }

    private fun getSkill() = viewModelScope.launch {
        profileRepo.getSkill()
            .onStart {
                responseSkill.value = SkillUiState.Loading
            }.catch {
                responseSkill.value = SkillUiState.Failure(it)
            }.collect {
                responseSkill.value = SkillUiState.Success(it)
            }
    }

    private fun getEducation() = viewModelScope.launch {
        profileRepo.getEducation()
            .onStart {
                responseEducation.value = EducationUiState.Loading
            }.catch {
                responseEducation.value = EducationUiState.Failure(it)
            }.collect {
                responseEducation.value = EducationUiState.Success(it)
            }
    }

    fun logout() {
       viewModelScope.launch {
           authPreferences.clearAuthToken()
       }
    }

}