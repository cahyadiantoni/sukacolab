package com.sukacolab.app.ui.feature.user.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.domain.model.TextFieldState
import com.sukacolab.app.ui.feature.user.search.ui_state.SearchUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val projectRepository: ProjectRepository
): ViewModel() {

    val response: MutableState<SearchUiState> = mutableStateOf(SearchUiState.Empty)

    private val _queryState = mutableStateOf(TextFieldState())
    val queryState: State<TextFieldState> = _queryState

    fun cekQuery(value:String){
        if (value.length < 3) {
            _queryState.value = queryState.value.copy(text = value, error = "Masukan setidaknya 3 karakter")
        } else {
            _queryState.value = queryState.value.copy(text = value, error = "")
            searchProject()
        }
    }
    fun searchProject() = viewModelScope.launch {
        projectRepository.searchProject(query = queryState.value.text)
            .onStart {
                response.value = SearchUiState.Loading
            }.catch {
                response.value = SearchUiState.Failure(it)
            }.collect {
                response.value = SearchUiState.Success(it)
                Log.d("SEARCH SUCCESS", "get Product")
            }
    }

}