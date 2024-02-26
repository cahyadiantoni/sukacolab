package com.sukacolab.app.ui.feature.user.profile.sub_screen.experience

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailExperienceUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.ExperienceUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExperienceViewModel(
    private val profileRepo: ProfileRepository,
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    val responseAllExperience: MutableState<ExperienceUiState> = mutableStateOf(ExperienceUiState.Empty)
    val responseDetailExperience: MutableState<DetailExperienceUiState> = mutableStateOf(DetailExperienceUiState.Empty)

    private val _deleteExperienceResult = MutableLiveData<DeleteExperienceResults>()
    val deleteExperienceResult: LiveData<DeleteExperienceResults> = _deleteExperienceResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        getAllExperience()
    }

    private fun getAllExperience() = viewModelScope.launch {
        profileRepo.getAllExperience()
            .onStart {
                responseAllExperience.value = ExperienceUiState.Loading
            }.catch {
                responseAllExperience.value = ExperienceUiState.Failure(it)
            }.collect {
                responseAllExperience.value = ExperienceUiState.Success(it)
            }
    }

    fun getDetailExperience(id: String) = viewModelScope.launch {
        profileRepo.getDetailExperience(id)
            .onStart {
                responseDetailExperience.value = DetailExperienceUiState.Loading
            }.catch {
                responseDetailExperience.value = DetailExperienceUiState.Failure(it)
            }.collect {
                responseDetailExperience.value = DetailExperienceUiState.Success(it)
            }
    }

    fun deleteExperience(id : String) = viewModelScope.launch {
        _isLoading.value = true
        try {
            val token = authPreferences.getAuthToken()
            apiService.deleteExperience(
                token = "Bearer $token",
                id = id
            ).enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    Log.d("DELETE EXPERIENCE", "delete experience success")
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        val success = response.body()!!.success
                        val message = response.body()!!.message

                        if(success) {
                            _deleteExperienceResult.value = DeleteExperienceResults.Success(message)
                        } else {
                            _deleteExperienceResult.value = DeleteExperienceResults.Error(message)
                        }
                    } else {
                        _isLoading.value = false
                        _deleteExperienceResult.value = DeleteExperienceResults.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("DELETE EXPERIENCE", "delete experience failed ${t.message}")
                    _isLoading.value = false
                    _deleteExperienceResult.value = DeleteExperienceResults.Error(t.localizedMessage ?: "Unknown error occurred")
                }

            })
        } catch (e: Exception) {
            Log.d("DELETE EXPERIENCE", "delete experience failed ${e.message}")
            _deleteExperienceResult.value = DeleteExperienceResults.Error(e.localizedMessage ?: "Unknown error occurred")
        }

    }
}

sealed class DeleteExperienceResults {
    data class Success(val message: String) : DeleteExperienceResults()
    data class Error(val errorMessage: String) : DeleteExperienceResults()
}