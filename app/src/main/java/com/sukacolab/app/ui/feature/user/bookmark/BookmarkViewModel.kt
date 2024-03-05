package com.sukacolab.app.ui.feature.user.bookmark

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.ui.feature.user.bookmark.ui_state.BookmarkUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseProject: MutableState<BookmarkUiState> = mutableStateOf(BookmarkUiState.Empty)

    fun getProject() = viewModelScope.launch {
        projectRepo.getBookmarkProject()
            .onStart {
                responseProject.value = BookmarkUiState.Loading
            }.catch {
                responseProject.value = BookmarkUiState.Failure(it)
            }.collect {
                responseProject.value = BookmarkUiState.Success(it)
            }
    }
}