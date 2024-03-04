package com.sukacolab.app.ui.feature.user.profile_other.sub_screen.experience

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.ExperienceUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExperienceOtherViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseAllExperience: MutableState<ExperienceUiState> = mutableStateOf(ExperienceUiState.Empty)

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun getAllExperience(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherExperience(0, userId)
            .onStart {
                responseAllExperience.value = ExperienceUiState.Loading
            }.catch {
                responseAllExperience.value = ExperienceUiState.Failure(it)
            }.collect {
                responseAllExperience.value = ExperienceUiState.Success(it)
            }
    }
}