package com.sukacolab.app.ui.feature.user.project.project_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.ui.feature.user.project.ui_state.DetailProjectUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProjectDetailViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseDetail: MutableState<DetailProjectUiState> = mutableStateOf(DetailProjectUiState.Empty)

    fun getDetailProject(projectId: String) = viewModelScope.launch {
        projectRepo.getDetailProject(projectId)
            .onStart {
                responseDetail.value = DetailProjectUiState.Loading
            }.catch {
                responseDetail.value = DetailProjectUiState.Failure(it)
            }.collect {
                responseDetail.value = DetailProjectUiState.Success(it)
            }
    }
}