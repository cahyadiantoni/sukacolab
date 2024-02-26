package com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.password

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.SettingPasswordRequest
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.di.ResourcesProvider
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingPasswordViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = SettingPasswordForm(resourcesProvider)

    private val _setPasswordResult = MutableLiveData<SetPasswordResults>()
    val setPasswordResult: LiveData<SetPasswordResults> = _setPasswordResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun validate() {
        form.validate(true)
        form.logRawValue()
        Log.d("MainViewModel", "Submit (form is valid: ${form.isValid})")
        Log.d("Password", "Password: ${form.password.state.value.toString()}")

        if(form.isValid){
            setPassword(oldPassword = form.oldPassword.state.value.toString(), password = form.password.state.value.toString())
        }
    }

    private fun setPassword(oldPassword: String, password: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val request = SettingPasswordRequest(oldPassword, password)
                apiService.setPassword(
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
                                _setPasswordResult.value = SetPasswordResults.Success(message)
                            } else {
                                _setPasswordResult.value = SetPasswordResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _setPasswordResult.value = SetPasswordResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _setPasswordResult.value = SetPasswordResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _setPasswordResult.value = SetPasswordResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class SetPasswordResults {
    data class Success(val message: String) : SetPasswordResults()
    data class Error(val errorMessage: String) : SetPasswordResults()
}