package com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.add_skill

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
import com.sukacolab.app.util.convertToYearMonthDayFormat
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddSkillViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = AddSkillForm(resourcesProvider)

    private val _addSkillResult = MutableLiveData<AddSkillResults>()
    val addSkillResult: LiveData<AddSkillResults> = _addSkillResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun validate() {
        form.validate(true)
        form.logRawValue()
        Log.d("MainViewModel", "Submit (form is valid: ${form.isValid})")
        Log.d("Add Skill", "name = ${form.name.state.value},\n" +
                "                description = ${form.description.state.value},\n")

        if(form.isValid){
            addSkill(
                name = form.name.state.value.toString(),
                description = form.description.state.value.toString(),
                )
        }
    }

    private fun addSkill(name: String, description: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val request = SkillRequest(
                    name, description,
                )
                apiService.addSkill(
                    token = "Bearer $token",
                    request = request
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
                                _addSkillResult.value = AddSkillResults.Success(message)
                            } else {
                                _addSkillResult.value = AddSkillResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _addSkillResult.value = AddSkillResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _addSkillResult.value = AddSkillResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _addSkillResult.value = AddSkillResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class AddSkillResults {
    data class Success(val message: String) : AddSkillResults()
    data class Error(val errorMessage: String) : AddSkillResults()
}