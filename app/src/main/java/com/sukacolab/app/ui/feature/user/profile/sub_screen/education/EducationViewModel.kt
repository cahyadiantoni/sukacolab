package com.sukacolab.app.ui.feature.user.profile.sub_screen.education

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
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailEducationUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.EducationUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EducationViewModel(
    private val profileRepo: ProfileRepository,
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    val responseAllEducation: MutableState<EducationUiState> = mutableStateOf(EducationUiState.Empty)
    val responseDetailEducation: MutableState<DetailEducationUiState> = mutableStateOf(DetailEducationUiState.Empty)

    private val _deleteEducationResult = MutableLiveData<DeleteEducationResults>()
    val deleteEducationResult: LiveData<DeleteEducationResults> = _deleteEducationResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        getAllEducation()
    }

    private fun getAllEducation() = viewModelScope.launch {
        profileRepo.getEducation(0)
            .onStart {
                responseAllEducation.value = EducationUiState.Loading
            }.catch {
                responseAllEducation.value = EducationUiState.Failure(it)
            }.collect {
                responseAllEducation.value = EducationUiState.Success(it)
            }
    }

    fun getDetailEducation(id: String) = viewModelScope.launch {
        profileRepo.getDetailEducation(id)
            .onStart {
                responseDetailEducation.value = DetailEducationUiState.Loading
            }.catch {
                responseDetailEducation.value = DetailEducationUiState.Failure(it)
            }.collect {
                responseDetailEducation.value = DetailEducationUiState.Success(it)
            }
    }
    fun deleteEducation(id : String) = viewModelScope.launch {
        _isLoading.value = true
        try {
            val token = authPreferences.getAuthToken()
            apiService.deleteEducation(
                token = "Bearer $token",
                id = id
            ).enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    Log.d("DELETE EXPERIENCE", "delete education success")
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        val success = response.body()!!.success
                        val message = response.body()!!.message

                        if(success) {
                            _deleteEducationResult.value = DeleteEducationResults.Success(message)
                        } else {
                            _deleteEducationResult.value = DeleteEducationResults.Error(message)
                        }
                    } else {
                        _isLoading.value = false
                        _deleteEducationResult.value = DeleteEducationResults.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("DELETE EXPERIENCE", "delete education failed ${t.message}")
                    _isLoading.value = false
                    _deleteEducationResult.value = DeleteEducationResults.Error(t.localizedMessage ?: "Unknown error occurred")
                }

            })
        } catch (e: Exception) {
            Log.d("DELETE EXPERIENCE", "delete education failed ${e.message}")
            _deleteEducationResult.value = DeleteEducationResults.Error(e.localizedMessage ?: "Unknown error occurred")
        }

    }
}

sealed class DeleteEducationResults {
    data class Success(val message: String) : DeleteEducationResults()
    data class Error(val errorMessage: String) : DeleteEducationResults()
}