package com.sukacolab.app.ui.feature.user.profile.sub_screen.certification.edit_certification

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

class EditCertificationViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = EditCertificationForm(resourcesProvider)

    private val _editCertificationResult = MutableLiveData<EditCertificationResults>()
    val editCertificationResult: LiveData<EditCertificationResults> = _editCertificationResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun validate(id: String) {
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
        Log.d("Edit Certification", "name = ${form.name.state.value},\n" +
                "                publisher = ${form.publisher.state.value},\n" +
                "                credential = ${form.credential.state.value},\n" +
                "                startDate = ${start},\n" +
                "                endDate = ${end},\n")

        if(form.isValid){
            editCertification(
                id = id,
                name = form.name.state.value.toString(),
                publisher = form.publisher.state.value.toString(),
                credential = form.credential.state.value.toString(),
                startDate = start,
                endDate = end
                )
        }
    }

    private fun editCertification(id: String, name: String, publisher: String, credential: String, startDate: String, endDate: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val request = CertificationRequest(
                    name, publisher, credential, startDate, endDate
                )
                apiService.editCertification(
                    id = id,
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
                                _editCertificationResult.value = EditCertificationResults.Success(message)
                            } else {
                                _editCertificationResult.value = EditCertificationResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _editCertificationResult.value = EditCertificationResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _editCertificationResult.value = EditCertificationResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _editCertificationResult.value = EditCertificationResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class EditCertificationResults {
    data class Success(val message: String) : EditCertificationResults()
    data class Error(val errorMessage: String) : EditCertificationResults()
}