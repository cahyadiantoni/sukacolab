package com.sukacolab.app.ui.feature.user.application

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.ui.feature.user.application.ui_state.ApplicationUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ApplicationViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseProject: MutableState<ApplicationUiState> = mutableStateOf(ApplicationUiState.Empty)

    init {
        getProject()
    }

    private fun getProject() = viewModelScope.launch {
        projectRepo.getJoinedProject()
            .onStart {
                responseProject.value = ApplicationUiState.Loading
            }.catch {
                responseProject.value = ApplicationUiState.Failure(it)
            }.collect {
                responseProject.value = ApplicationUiState.Success(it)
            }
    }
}