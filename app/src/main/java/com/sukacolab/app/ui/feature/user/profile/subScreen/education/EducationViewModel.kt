package com.sukacolab.app.ui.feature.user.profile.subScreen.education

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.ui.feature.user.profile.ui_state.EducationUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class EducationViewModel(
    private val profileRepo: ProfileRepository,
) : ViewModel() {
    val responseAllEducation: MutableState<EducationUiState> = mutableStateOf(EducationUiState.Empty)

    init {
        getAllEducation()
    }

    private fun getAllEducation() = viewModelScope.launch {
        profileRepo.getAllEducation()
            .onStart {
                responseAllEducation.value = EducationUiState.Loading
            }.catch {
                responseAllEducation.value = EducationUiState.Failure(it)
            }.collect {
                responseAllEducation.value = EducationUiState.Success(it)
            }
    }
}