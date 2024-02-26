package com.sukacolab.app.ui.feature.user.profile.sub_screen.certification

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
import com.sukacolab.app.ui.feature.user.profile.ui_state.CertificationUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailCertificationUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CertificationViewModel(
    private val profileRepo: ProfileRepository,
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    val responseAllCertification: MutableState<CertificationUiState> = mutableStateOf(CertificationUiState.Empty)
    val responseDetailCertification: MutableState<DetailCertificationUiState> = mutableStateOf(DetailCertificationUiState.Empty)

    private val _deleteCertificationResult = MutableLiveData<DeleteCertificationResults>()
    val deleteCertificationResult: LiveData<DeleteCertificationResults> = _deleteCertificationResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        getAllCertification()
    }

    private fun getAllCertification() = viewModelScope.launch {
        profileRepo.getAllCertification()
            .onStart {
                responseAllCertification.value = CertificationUiState.Loading
            }.catch {
                responseAllCertification.value = CertificationUiState.Failure(it)
            }.collect {
                responseAllCertification.value = CertificationUiState.Success(it)
            }
    }

    fun getDetailCertification(id: String) = viewModelScope.launch {
        profileRepo.getDetailCertification(id)
            .onStart {
                responseDetailCertification.value = DetailCertificationUiState.Loading
            }.catch {
                responseDetailCertification.value = DetailCertificationUiState.Failure(it)
            }.collect {
                responseDetailCertification.value = DetailCertificationUiState.Success(it)
            }
    }
    fun deleteCertification(id : String) = viewModelScope.launch {
        _isLoading.value = true
        try {
            val token = authPreferences.getAuthToken()
            apiService.deleteCertification(
                token = "Bearer $token",
                id = id
            ).enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    Log.d("DELETE EXPERIENCE", "delete certification success")
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        val success = response.body()!!.success
                        val message = response.body()!!.message

                        if(success) {
                            _deleteCertificationResult.value = DeleteCertificationResults.Success(message)
                        } else {
                            _deleteCertificationResult.value = DeleteCertificationResults.Error(message)
                        }
                    } else {
                        _isLoading.value = false
                        _deleteCertificationResult.value = DeleteCertificationResults.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("DELETE EXPERIENCE", "delete certification failed ${t.message}")
                    _isLoading.value = false
                    _deleteCertificationResult.value = DeleteCertificationResults.Error(t.localizedMessage ?: "Unknown error occurred")
                }

            })
        } catch (e: Exception) {
            Log.d("DELETE EXPERIENCE", "delete certification failed ${e.message}")
            _deleteCertificationResult.value = DeleteCertificationResults.Error(e.localizedMessage ?: "Unknown error occurred")
        }

    }
}

sealed class DeleteCertificationResults {
    data class Success(val message: String) : DeleteCertificationResults()
    data class Error(val errorMessage: String) : DeleteCertificationResults()
}