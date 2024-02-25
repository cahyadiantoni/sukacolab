package com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.email

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.SettingEmailRequest
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.di.ResourcesProvider
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingEmailViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = SettingEmailForm(resourcesProvider)

    private val _setEmailResult = MutableLiveData<SetEmailResults>()
    val setEmailResult: LiveData<SetEmailResults> = _setEmailResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun validate() {
        form.validate(true)
        form.logRawValue()
        Log.d("MainViewModel", "Submit (form is valid: ${form.isValid})")
        Log.d("Email", "Email: ${form.email.state.value.toString()}")

        if(form.isValid){
            setEmail(email = form.email.state.value.toString())
        }
    }

    private fun setEmail(email: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val request = SettingEmailRequest(email)
                apiService.setEmail(
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
                                _setEmailResult.value = SetEmailResults.Success(message)
                            } else {
                                _setEmailResult.value = SetEmailResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _setEmailResult.value = SetEmailResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _setEmailResult.value = SetEmailResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _setEmailResult.value = SetEmailResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class SetEmailResults {
    data class Success(val message: String) : SetEmailResults()
    data class Error(val errorMessage: String) : SetEmailResults()
}