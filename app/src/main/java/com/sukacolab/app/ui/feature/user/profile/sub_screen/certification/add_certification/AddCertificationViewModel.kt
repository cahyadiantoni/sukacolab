package com.sukacolab.app.ui.feature.user.profile.sub_screen.certification.add_certification

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.CertificationRequest
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.util.convertToYearMonthDayFormat
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCertificationViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = AddCertificationForm(resourcesProvider)

    private val _addCertificationResult = MutableLiveData<AddCertificationResults>()
    val addCertificationResult: LiveData<AddCertificationResults> = _addCertificationResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun validate() {
        form.validate(true)
        form.logRawValue()
        Log.d("MainViewModel", "Submit (form is valid: ${form.isValid})")
        val start = if (form.startDate.state.value != null) {
            form.startDate.state.value.toString().convertToYearMonthDayFormat()
        } else {
            ""
        }
        val end = if (form.endDate.state.value != null) {
            form.endDate.state.value.toString().convertToYearMonthDayFormat()
        } else {
            ""
        }

        if(form.isValid){
            addCertification(
                name = form.name.state.value.toString(),
                publisher = form.publisher.state.value.toString(),
                credential = form.credential.state.value.toString(),
                startDate = start,
                endDate = end
                )
        }
    }

    private fun addCertification(name: String, publisher: String, credential: String, startDate: String, endDate: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val request = CertificationRequest(
                    name, publisher, credential, startDate, endDate
                )
                apiService.addCertification(
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
                                _addCertificationResult.value = AddCertificationResults.Success(message)
                            } else {
                                _addCertificationResult.value = AddCertificationResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _addCertificationResult.value = AddCertificationResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _addCertificationResult.value = AddCertificationResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _addCertificationResult.value = AddCertificationResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class AddCertificationResults {
    data class Success(val message: String) : AddCertificationResults()
    data class Error(val errorMessage: String) : AddCertificationResults()
}