package com.sukacolab.app.ui.feature.user.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.ui.feature.user.category.ui_state.CategoryUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseCategory: MutableState<CategoryUiState> = mutableStateOf(CategoryUiState.Empty)

    fun categoryProject(category: String) = viewModelScope.launch {
        projectRepo.searchProject(category)
            .onStart {
                responseCategory.value = CategoryUiState.Loading
            }.catch {
                responseCategory.value = CategoryUiState.Failure(it)
            }.collect {
                responseCategory.value = CategoryUiState.Success(it)
            }
    }
}