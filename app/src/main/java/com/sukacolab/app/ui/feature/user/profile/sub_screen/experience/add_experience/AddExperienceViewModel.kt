package com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.add_experience

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.ExperienceRequest
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.util.convertToYearMonthDayFormat
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddExperienceViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = AddExperienceForm(resourcesProvider)

    private val _addExperienceResult = MutableLiveData<AddExperienceResults>()
    val addExperienceResult: LiveData<AddExperienceResults> = _addExperienceResult

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
        Log.d("Add Experience", "title = ${form.title.state.value},\n" +
                "                company = ${form.company.state.value},\n" +
                "                role = ${form.role.state.value!!.name},\n" +
                "                startDate = ${start},\n" +
                "                endDate = ${end},\n" +
                "                isNow = ${form.isNow.state.value!!}")

        if(form.isValid){
            addExperience(
                title = form.title.state.value.toString(),
                company = form.company.state.value.toString(),
                role = form.role.state.value!!.name,
                startDate = start,
                endDate = end,
                isNow = form.isNow.state.value!!
                )
        }
    }

    private fun addExperience(title: String, company: String, role: String, startDate: String, endDate: String, isNow: Boolean){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val request = ExperienceRequest(
                    title, company, role, startDate, endDate, isNow
                )
                apiService.addExperience(
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
                                _addExperienceResult.value = AddExperienceResults.Success(message)
                            } else {
                                _addExperienceResult.value = AddExperienceResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _addExperienceResult.value = AddExperienceResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _addExperienceResult.value = AddExperienceResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _addExperienceResult.value = AddExperienceResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class AddExperienceResults {
    data class Success(val message: String) : AddExperienceResults()
    data class Error(val errorMessage: String) : AddExperienceResults()
}