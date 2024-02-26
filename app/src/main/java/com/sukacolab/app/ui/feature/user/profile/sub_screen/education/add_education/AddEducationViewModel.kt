package com.sukacolab.app.ui.feature.user.profile.sub_screen.education.add_education

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.EducationRequest
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.util.convertToYearMonthDayFormat
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddEducationViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = AddEducationForm(resourcesProvider)

    private val _addEducationResult = MutableLiveData<AddEducationResults>()
    val addEducationResult: LiveData<AddEducationResults> = _addEducationResult

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
        Log.d("Add Education", "Instansi = ${form.instansi.state.value},\n" +
                "                major = ${form.major.state.value},\n" +
                "                startDate = ${start},\n" +
                "                endDate = ${end},\n" +
                "                isNow = ${form.isNow.state.value!!}")

        if(form.isValid){
            addEducation(
                instansi = form.instansi.state.value.toString(),
                major = form.major.state.value.toString(),
                startDate = start,
                endDate = end,
                isNow = form.isNow.state.value!!
                )
        }
    }

    private fun addEducation(instansi: String, major: String, startDate: String, endDate: String, isNow: Boolean){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val request = EducationRequest(
                    instansi, major, startDate, endDate, isNow
                )
                apiService.addEducation(
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
                                _addEducationResult.value = AddEducationResults.Success(message)
                            } else {
                                _addEducationResult.value = AddEducationResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _addEducationResult.value = AddEducationResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _addEducationResult.value = AddEducationResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _addEducationResult.value = AddEducationResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class AddEducationResults {
    data class Success(val message: String) : AddEducationResults()
    data class Error(val errorMessage: String) : AddEducationResults()
}