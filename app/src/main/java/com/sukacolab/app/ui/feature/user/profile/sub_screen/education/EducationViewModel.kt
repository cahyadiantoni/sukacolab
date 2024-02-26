package com.sukacolab.app.ui.feature.user.profile.sub_screen.education

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailEducationUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.EducationUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class EducationViewModel(
    private val profileRepo: ProfileRepository,
) : ViewModel() {
    val responseAllEducation: MutableState<EducationUiState> = mutableStateOf(EducationUiState.Empty)
    val responseDetailEducation: MutableState<DetailEducationUiState> = mutableStateOf(DetailEducationUiState.Empty)

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

    fun getDetailEducation(id: String) = viewModelScope.launch {
        profileRepo.getDetailEducation(id)
            .onStart {
                responseDetailEducation.value = DetailEducationUiState.Loading
            }.catch {
                responseDetailEducation.value = DetailEducationUiState.Failure(it)
            }.collect {
                responseDetailEducation.value = DetailEducationUiState.Success(it)
            }
    }
}