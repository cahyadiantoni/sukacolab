package com.sukacolab.app.ui.feature.user.profile.sub_screen.profile_edit

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.EditMeRequest
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.util.convertToYearMonthDayFormat
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileEditViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = ProfileEditForm(resourcesProvider)

    private val _editProfileResult = MutableLiveData<ProfileEditResults>()
    val editProfileResult: LiveData<ProfileEditResults> = _editProfileResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun validate() {
        form.validate(true)
        form.logRawValue()
        Log.d("MainViewModel", "Submit (form is valid: ${form.isValid})")
        val name = if (form.name.state.value != null) {
            form.name.state.value.toString()
        } else {
            ""
        }
        
        val summary = if (form.summary.state.value != null) {
            form.summary.state.value.toString()
        } else {
            ""
        }

        val linkedin = if (form.linkedin.state.value != null) {
            form.linkedin.state.value.toString()
        } else {
            ""
        }

        val github = if (form.github.state.value != null) {
            form.github.state.value.toString()
        } else {
            ""
        }

        val whatsapp = if (form.whatsapp.state.value != null) {
            form.whatsapp.state.value.toString()
        } else {
            ""
        }

        val instagram = if (form.instagram.state.value != null) {
            form.instagram.state.value.toString()
        } else {
            ""
        }

        if (form.isValid) {
            editProfile(
                name = name,
                summary = summary,
                linkedin = linkedin,
                github = github,
                whatsapp = whatsapp,
                instagram = instagram
            )
        }

    }

    private fun editProfile(name: String, summary: String, linkedin: String, github: String, whatsapp: String, instagram: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val request = EditMeRequest(
                    name, summary, linkedin, github, whatsapp, instagram
                )
                apiService.editProfile(
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
                                _editProfileResult.value = ProfileEditResults.Success(message)
                            } else {
                                _editProfileResult.value = ProfileEditResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _editProfileResult.value = ProfileEditResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _editProfileResult.value = ProfileEditResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _editProfileResult.value = ProfileEditResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class ProfileEditResults {
    data class Success(val message: String) : ProfileEditResults()
    data class Error(val errorMessage: String) : ProfileEditResults()
}