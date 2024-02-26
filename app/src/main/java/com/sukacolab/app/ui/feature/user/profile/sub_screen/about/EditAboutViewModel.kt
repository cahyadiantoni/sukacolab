package com.sukacolab.app.ui.feature.user.profile.sub_screen.about

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.EditAboutRequest
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.di.ResourcesProvider
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditAboutViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = EditAboutForm(resourcesProvider)

    private val _editAboutResult = MutableLiveData<EditAboutResults>()
    val editAboutResult: LiveData<EditAboutResults> = _editAboutResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun validate() {
        form.validate(true)
        form.logRawValue()
        Log.d("MainViewModel", "Submit (form is valid: ${form.isValid})")
        Log.d("About", "About: ${form.about.state.value.toString()}")

        if(form.isValid){
            editAbout(about = form.about.state.value.toString())
        }
    }

    private fun editAbout(about: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val request = EditAboutRequest(about)
                apiService.editAbout(
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
                                _editAboutResult.value = EditAboutResults.Success(message)
                            } else {
                                _editAboutResult.value = EditAboutResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _editAboutResult.value = EditAboutResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _editAboutResult.value = EditAboutResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _editAboutResult.value = EditAboutResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class EditAboutResults {
    data class Success(val message: String) : EditAboutResults()
    data class Error(val errorMessage: String) : EditAboutResults()
}