package com.sukacolab.app.ui.feature.admin.review

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
import com.sukacolab.app.ui.feature.admin.review.ui_state.ReviewUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseReview: MutableState<ReviewUiState> = mutableStateOf(ReviewUiState.Empty)

    fun getReview() = viewModelScope.launch {
        projectRepo.getReviewProject()
            .onStart {
                responseReview.value = ReviewUiState.Loading
            }.catch {
                responseReview.value = ReviewUiState.Failure(it)
            }.collect {
                responseReview.value = ReviewUiState.Success(it)
            }
    }
}
