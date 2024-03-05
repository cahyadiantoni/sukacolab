package com.sukacolab.app.ui.feature.user.ur_project

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
import com.sukacolab.app.ui.feature.user.ur_project.ui_state.UrProjectUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UrProjectViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseUrProject: MutableState<UrProjectUiState> = mutableStateOf(UrProjectUiState.Empty)

    fun getUrProject() = viewModelScope.launch {
        projectRepo.getUrProject()
            .onStart {
                responseUrProject.value = UrProjectUiState.Loading
            }.catch {
                responseUrProject.value = UrProjectUiState.Failure(it)
            }.collect {
                responseUrProject.value = UrProjectUiState.Success(it)
            }
    }
}
