package com.sukacolab.app.ui.feature.user.profile_other

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.CertificationUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.EducationUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.ExperienceUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.ProfileUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.SkillUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProfileOtherViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val response: MutableState<ProfileUiState> = mutableStateOf(ProfileUiState.Empty)
    val responseExperience: MutableState<ExperienceUiState> = mutableStateOf(ExperienceUiState.Empty)
    val responseCertification: MutableState<CertificationUiState> = mutableStateOf(
        CertificationUiState.Empty)
    val responseSkill: MutableState<SkillUiState> = mutableStateOf(SkillUiState.Empty)
    val responseEducation: MutableState<EducationUiState> = mutableStateOf(EducationUiState.Empty)

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun getProfileOther(userId: String, projectId: String) = viewModelScope.launch {
        projectRepo.getProfileUserJoin(userId, projectId)
            .onStart {
                response.value = ProfileUiState.Loading
            }.catch {
                response.value = ProfileUiState.Failure(it)
            }.collect {
                response.value = ProfileUiState.Success(it)
            }
    }

    fun getExperience(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherExperience(2, userId)
            .onStart {
                responseExperience.value = ExperienceUiState.Loading
            }.catch {
                responseExperience.value = ExperienceUiState.Failure(it)
            }.collect {
                responseExperience.value = ExperienceUiState.Success(it)
            }
    }

    fun getCertification(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherCertification(2, userId)
            .onStart {
                responseCertification.value = CertificationUiState.Loading
            }.catch {
                responseCertification.value = CertificationUiState.Failure(it)
            }.collect {
                responseCertification.value = CertificationUiState.Success(it)
            }
    }

    fun getSkill(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherSkill(2, userId)
            .onStart {
                responseSkill.value = SkillUiState.Loading
            }.catch {
                responseSkill.value = SkillUiState.Failure(it)
            }.collect {
                responseSkill.value = SkillUiState.Success(it)
            }
    }

    fun getEducation(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherEducation(2, userId)
            .onStart {
                responseEducation.value = EducationUiState.Loading
            }.catch {
                responseEducation.value = EducationUiState.Failure(it)
            }.collect {
                responseEducation.value = EducationUiState.Success(it)
            }
    }
}