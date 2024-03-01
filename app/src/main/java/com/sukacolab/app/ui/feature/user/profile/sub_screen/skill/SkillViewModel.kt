package com.sukacolab.app.ui.feature.user.profile.sub_screen.skill

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
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailSkillUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.SkillUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SkillViewModel(
    private val profileRepo: ProfileRepository,
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    val responseAllSkill: MutableState<SkillUiState> = mutableStateOf(SkillUiState.Empty)
    val responseDetailSkill: MutableState<DetailSkillUiState> = mutableStateOf(
        DetailSkillUiState.Empty)

    private val _deleteSkillResult = MutableLiveData<DeleteSkillResults>()
    val deleteSkillResult: LiveData<DeleteSkillResults> = _deleteSkillResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        getAllSkill()
    }

    private fun getAllSkill() = viewModelScope.launch {
        profileRepo.getSkill(0)
            .onStart {
                responseAllSkill.value = SkillUiState.Loading
            }.catch {
                responseAllSkill.value = SkillUiState.Failure(it)
            }.collect {
                responseAllSkill.value = SkillUiState.Success(it)
            }
    }

    fun getDetailSkill(id: String) = viewModelScope.launch {
        profileRepo.getDetailSkill(id)
            .onStart {
                responseDetailSkill.value = DetailSkillUiState.Loading
            }.catch {
                responseDetailSkill.value = DetailSkillUiState.Failure(it)
            }.collect {
                responseDetailSkill.value = DetailSkillUiState.Success(it)
            }
    }
    fun deleteSkill(id : String) = viewModelScope.launch {
        _isLoading.value = true
        try {
            val token = authPreferences.getAuthToken()
            apiService.deleteSkill(
                token = "Bearer $token",
                id = id
            ).enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    Log.d("DELETE EXPERIENCE", "delete skill success")
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        val success = response.body()!!.success
                        val message = response.body()!!.message

                        if(success) {
                            _deleteSkillResult.value = DeleteSkillResults.Success(message)
                        } else {
                            _deleteSkillResult.value = DeleteSkillResults.Error(message)
                        }
                    } else {
                        _isLoading.value = false
                        _deleteSkillResult.value = DeleteSkillResults.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("DELETE EXPERIENCE", "delete skill failed ${t.message}")
                    _isLoading.value = false
                    _deleteSkillResult.value = DeleteSkillResults.Error(t.localizedMessage ?: "Unknown error occurred")
                }

            })
        } catch (e: Exception) {
            Log.d("DELETE EXPERIENCE", "delete skill failed ${e.message}")
            _deleteSkillResult.value = DeleteSkillResults.Error(e.localizedMessage ?: "Unknown error occurred")
        }

    }
}

sealed class DeleteSkillResults {
    data class Success(val message: String) : DeleteSkillResults()
    data class Error(val errorMessage: String) : DeleteSkillResults()
}