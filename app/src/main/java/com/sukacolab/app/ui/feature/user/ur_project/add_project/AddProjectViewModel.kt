package com.sukacolab.app.ui.feature.user.ur_project.add_project

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.ProjectRequest
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.util.convertToYearMonthDayFormat
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProjectViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = AddProjectForm(resourcesProvider)

    private val _addProjectResult = MutableLiveData<AddProjectResults>()
    val addProjectResult: LiveData<AddProjectResults> = _addProjectResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun validate() {
        form.validate(true)
        form.logRawValue()
        Log.d("MainViewModel", "Submit (form is valid: ${form.isValid})")
        val position = if (form.otherPosition.state.value == null) {
            form.position.state.value!!.name
        } else {
            form.otherPosition.state.value
        }

        val location = if (form.isRemote.state.value!!) {
            "Remote"
        } else {
            form.location.state.value
        }

        val tipe = if (form.otherTipe.state.value == null) {
            form.tipe.state.value!!.name
        } else {
            form.otherTipe.state.value
        }

        val isPaid = form.tipe.state.value!!.name == "Loker"

        if(form.isValid){
            addProject(
                name = form.name.state.value.toString(),
                position = position.toString(),
                location = location.toString(),
                tipe = tipe.toString(),
                is_paid = isPaid,
                time = form.time.state.value!!.name,
                description = form.description.state.value.toString(),
                requirements = form.requirements.state.value.toString(),
                )
        }
    }

    private fun addProject(name: String, position: String, location: String, tipe: String, is_paid: Boolean, time: String, description: String, requirements: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val request = ProjectRequest(
                    name, position, location, tipe, is_paid, time, description, requirements
                )
                apiService.addProject(
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
                                _addProjectResult.value = AddProjectResults.Success(message)
                            } else {
                                _addProjectResult.value = AddProjectResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _addProjectResult.value = AddProjectResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _addProjectResult.value = AddProjectResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _addProjectResult.value = AddProjectResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class AddProjectResults {
    data class Success(val message: String) : AddProjectResults()
    data class Error(val errorMessage: String) : AddProjectResults()
}