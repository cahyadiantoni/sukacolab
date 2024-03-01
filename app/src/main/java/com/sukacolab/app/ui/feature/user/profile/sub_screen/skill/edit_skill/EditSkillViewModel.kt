package com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.edit_skill

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.SkillRequest
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.di.ResourcesProvider
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditSkillViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = EditSkillForm(resourcesProvider)

    private val _editSkillResult = MutableLiveData<EditSkillResults>()
    val editSkillResult: LiveData<EditSkillResults> = _editSkillResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun validate(id: String) {
        form.validate(true)
        form.logRawValue()
        Log.d("MainViewModel", "Submit (form is valid: ${form.isValid})")

        if(form.isValid){
            editSkill(
                id = id,
                name = form.name.state.value.toString(),
                description = form.description.state.value.toString(),
                )
        }
    }

    private fun editSkill(id: String, name: String, description: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val request = SkillRequest(
                    name, description,
                )
                apiService.editSkill(
                    token = "Bearer $token",
                    request = request,
                    id = id
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
                                _editSkillResult.value = EditSkillResults.Success(message)
                            } else {
                                _editSkillResult.value = EditSkillResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _editSkillResult.value = EditSkillResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _editSkillResult.value = EditSkillResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _editSkillResult.value = EditSkillResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class EditSkillResults {
    data class Success(val message: String) : EditSkillResults()
    data class Error(val errorMessage: String) : EditSkillResults()
}