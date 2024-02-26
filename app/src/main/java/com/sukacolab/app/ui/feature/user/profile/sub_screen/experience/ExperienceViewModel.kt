package com.sukacolab.app.ui.feature.user.profile.sub_screen.experience

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailExperienceUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.ExperienceUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.ProfileUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ExperienceViewModel(
    private val profileRepo: ProfileRepository,
) : ViewModel() {
    val responseAllExperience: MutableState<ExperienceUiState> = mutableStateOf(ExperienceUiState.Empty)
    val responseDetailExperience: MutableState<DetailExperienceUiState> = mutableStateOf(DetailExperienceUiState.Empty)

    init {
        getAllExperience()
    }

    private fun getAllExperience() = viewModelScope.launch {
        profileRepo.getAllExperience()
            .onStart {
                responseAllExperience.value = ExperienceUiState.Loading
            }.catch {
                responseAllExperience.value = ExperienceUiState.Failure(it)
            }.collect {
                responseAllExperience.value = ExperienceUiState.Success(it)
            }
    }

    fun getDetailExperience(id: String) = viewModelScope.launch {
        profileRepo.getDetailExperience(id)
            .onStart {
                responseDetailExperience.value = DetailExperienceUiState.Loading
            }.catch {
                responseDetailExperience.value = DetailExperienceUiState.Failure(it)
            }.collect {
                responseDetailExperience.value = DetailExperienceUiState.Success(it)
            }
    }
}