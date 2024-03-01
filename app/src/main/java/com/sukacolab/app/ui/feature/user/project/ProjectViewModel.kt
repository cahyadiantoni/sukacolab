package com.sukacolab.app.ui.feature.user.project

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.ui.feature.user.project.ui_state.ProjectUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProjectViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseProject: MutableState<ProjectUiState> = mutableStateOf(ProjectUiState.Empty)

    init {
        getProject()
    }

    private fun getProject() = viewModelScope.launch {
        projectRepo.getProject(1, 0)
            .onStart {
                responseProject.value = ProjectUiState.Loading
            }.catch {
                responseProject.value = ProjectUiState.Failure(it)
            }.collect {
                responseProject.value = ProjectUiState.Success(it)
            }
    }
}