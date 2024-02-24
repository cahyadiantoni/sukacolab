package com.sukacolab.app.ui.feature.user.profile.subScreen.experience

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.ui.feature.user.profile.ui_state.ExperienceUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ExperienceViewModel(
    private val profileRepo: ProfileRepository,
) : ViewModel() {
    val responseAllExperience: MutableState<ExperienceUiState> = mutableStateOf(ExperienceUiState.Empty)

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
}