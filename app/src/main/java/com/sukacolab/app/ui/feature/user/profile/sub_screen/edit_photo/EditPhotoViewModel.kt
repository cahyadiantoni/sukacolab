package com.sukacolab.app.ui.feature.user.profile.sub_screen.edit_photo

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.response.BaseResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class EditPhotoViewModel(
    private val authPreferences: AuthPreferences,
    private val apiService: ApiService,
) : ViewModel()
{
    private val _editPhotoResult = MutableLiveData<EditPhotoResults>()
    val editPhotoResult: LiveData<EditPhotoResults> = _editPhotoResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun editPhoto(imageFile: File){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val imageBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
                val imgMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData("photo", imageFile.name, imageBody)
                apiService.changePhoto(
                    token = "Bearer $token",
                    photo = imgMultiPart
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
                                _editPhotoResult.value = EditPhotoResults.Success(message)
                            } else {
                                _editPhotoResult.value = EditPhotoResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _editPhotoResult.value = EditPhotoResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _editPhotoResult.value = EditPhotoResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _editPhotoResult.value = EditPhotoResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class EditPhotoResults {
    data class Success(val message: String) : EditPhotoResults()
    data class Error(val errorMessage: String) : EditPhotoResults()
}