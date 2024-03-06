package com.sukacolab.app.ui.feature.user.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.ui.feature.user.profile.ui_state.CertificationUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.EducationUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.ExperienceUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.ProfileUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.SkillUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ProfileViewModel(
    private val authPreferences: AuthPreferences,
    private val profileRepo: ProfileRepository,
    private val apiService: ApiService,
) : ViewModel() {
    val response: MutableState<ProfileUiState> = mutableStateOf(ProfileUiState.Empty)
    val responseExperience: MutableState<ExperienceUiState> = mutableStateOf(ExperienceUiState.Empty)
    val responseCertification: MutableState<CertificationUiState> = mutableStateOf(
        CertificationUiState.Empty)
    val responseSkill: MutableState<SkillUiState> = mutableStateOf(SkillUiState.Empty)
    val responseEducation: MutableState<EducationUiState> = mutableStateOf(EducationUiState.Empty)

    private val _editResumeResult = MutableLiveData<EditResumeResults>()
    val editResumeResult: LiveData<EditResumeResults> = _editResumeResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    val id: Int?
        get() = (response.value as? ProfileUiState.Success)?.data?.id
    val name: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.name
    val email: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.email
    val photo: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.photo
    val summary: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.summary
    val linkedin: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.linkedin
    val github: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.github
    val whatsapp: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.whatsapp
    val instagram: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.instagram
    val resume: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.resume
    val about: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.about

    val isComplete: Boolean?
        get() = (response.value as? ProfileUiState.Success)?.data?.isComplete

    init {
        profileDetails()
    }

    fun profileDetails() = viewModelScope.launch {
        profileRepo.profileDetails()
            .onStart {
                response.value = ProfileUiState.Loading
            }.catch {
                response.value = ProfileUiState.Failure(it)
            }.collect {
                response.value = ProfileUiState.Success(it)
            }
    }

    fun getExperience() = viewModelScope.launch {
        profileRepo.getExperience(2)
            .onStart {
                responseExperience.value = ExperienceUiState.Loading
            }.catch {
                responseExperience.value = ExperienceUiState.Failure(it)
            }.collect {
                responseExperience.value = ExperienceUiState.Success(it)
            }
    }

    fun getCertification() = viewModelScope.launch {
        profileRepo.getCertification(2)
            .onStart {
                responseCertification.value = CertificationUiState.Loading
            }.catch {
                responseCertification.value = CertificationUiState.Failure(it)
            }.collect {
                responseCertification.value = CertificationUiState.Success(it)
            }
    }

    fun getSkill() = viewModelScope.launch {
        profileRepo.getSkill(2)
            .onStart {
                responseSkill.value = SkillUiState.Loading
            }.catch {
                responseSkill.value = SkillUiState.Failure(it)
            }.collect {
                responseSkill.value = SkillUiState.Success(it)
            }
    }

    fun getEducation() = viewModelScope.launch {
        profileRepo.getEducation(2)
            .onStart {
                responseEducation.value = EducationUiState.Loading
            }.catch {
                responseEducation.value = EducationUiState.Failure(it)
            }.collect {
                responseEducation.value = EducationUiState.Success(it)
            }
    }

    fun logout() {
       viewModelScope.launch {
           authPreferences.clearAuthToken()
       }
    }

    fun editResume(pdfFile: File) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                val pdfBody = pdfFile.asRequestBody("application/pdf".toMediaTypeOrNull())
                val pdfMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData("resume", pdfFile.name, pdfBody)
                apiService.changeResume(
                    token = "Bearer $token",
                    resume = pdfMultiPart
                ).enqueue(object: Callback<BaseResponse> {
                    override fun onResponse(
                        call: Call<BaseResponse>,
                        response: Response<BaseResponse>,
                    ) {
                        if (response.isSuccessful) {
                            _isLoading.value = false
                            val success = response.body()!!.success
                            val message = response.body()!!.message

                            if (success) {
                                _editResumeResult.value = EditResumeResults.Success(message)
                            } else {
                                _editResumeResult.value = EditResumeResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _editResumeResult.value = EditResumeResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _editResumeResult.value = EditResumeResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _editResumeResult.value = EditResumeResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }


}

sealed class EditResumeResults {
    data class Success(val message: String) : EditResumeResults()
    data class Error(val errorMessage: String) : EditResumeResults()
}