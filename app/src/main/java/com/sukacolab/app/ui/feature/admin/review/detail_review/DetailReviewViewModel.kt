package com.sukacolab.app.ui.feature.admin.review.detail_review

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.ui.feature.user.ur_project.ui_state.UrDetailProjectUiState
import com.sukacolab.app.ui.feature.user.ur_project.ui_state.UserJoinUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailReviewViewModel(
    private val projectRepo: ProjectRepository,
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    val responseDetail: MutableState<UrDetailProjectUiState> = mutableStateOf(UrDetailProjectUiState.Empty)

    private val _reviewProjectResult = MutableLiveData<ReviewProjectResults>()
    val reviewProjectResult: LiveData<ReviewProjectResults> = _reviewProjectResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun getDetailProject(projectId: String) = viewModelScope.launch {
        projectRepo.getDetailProject(projectId)
            .onStart {
                responseDetail.value = UrDetailProjectUiState.Loading
            }.catch {
                responseDetail.value = UrDetailProjectUiState.Failure(it)
            }.collect {
                responseDetail.value = UrDetailProjectUiState.Success(it)
            }
    }

    fun reviewProject(projectId: String, review: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                apiService.reviewProject(
                    token = "Bearer $token",
                    projectId = projectId,
                    review = review
                ).enqueue(object: Callback<BaseResponse> {
                    override fun onResponse(
                        call: Call<BaseResponse>,
                        response: Response<BaseResponse>,
                    ) {
                        if (response.isSuccessful) {
                            _isLoading.value = false
                            val success = response.body()!!.success
                            val message = response.body()!!.message

                            if(success) {
                                _reviewProjectResult.value = ReviewProjectResults.Success(message)
                            } else {
                                _reviewProjectResult.value = ReviewProjectResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _reviewProjectResult.value = ReviewProjectResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _reviewProjectResult.value = ReviewProjectResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _reviewProjectResult.value = ReviewProjectResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class ReviewProjectResults {
    data class Success(val message: String) : ReviewProjectResults()
    data class Error(val errorMessage: String) : ReviewProjectResults()
}