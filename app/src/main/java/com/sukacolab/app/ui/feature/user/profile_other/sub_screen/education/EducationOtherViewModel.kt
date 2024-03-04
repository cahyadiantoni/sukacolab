package com.sukacolab.app.ui.feature.user.profile_other.sub_screen.education

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
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailEducationUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.EducationUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EducationOtherViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseAllEducation: MutableState<EducationUiState> = mutableStateOf(EducationUiState.Empty)

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    fun getAllEducation(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherEducation(0, userId)
            .onStart {
                responseAllEducation.value = EducationUiState.Loading
            }.catch {
                responseAllEducation.value = EducationUiState.Failure(it)
            }.collect {
                responseAllEducation.value = EducationUiState.Success(it)
            }
    }
}