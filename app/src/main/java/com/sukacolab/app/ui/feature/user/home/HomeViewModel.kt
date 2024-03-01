package com.sukacolab.app.ui.feature.user.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.ui.feature.user.home.ui_state.HomeUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseProject: MutableState<HomeUiState> = mutableStateOf(HomeUiState.Empty)

    init {
        getProject()
    }

    private fun getProject() = viewModelScope.launch {
        projectRepo.getProject(1, 5)
            .onStart {
                responseProject.value = HomeUiState.Loading
            }.catch {
                responseProject.value = HomeUiState.Failure(it)
            }.collect {
                responseProject.value = HomeUiState.Success(it)
            }
    }
}