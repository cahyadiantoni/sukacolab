package com.sukacolab.app.ui.feature.user.profile_other

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.CertificationUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.EducationUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.ExperienceUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.ProfileUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.SkillUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileOtherViewModel(
    private val projectRepo: ProjectRepository,
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) : ViewModel() {
    val response: MutableState<ProfileUiState> = mutableStateOf(ProfileUiState.Empty)
    val responseExperience: MutableState<ExperienceUiState> = mutableStateOf(ExperienceUiState.Empty)
    val responseCertification: MutableState<CertificationUiState> = mutableStateOf(
        CertificationUiState.Empty)
    val responseSkill: MutableState<SkillUiState> = mutableStateOf(SkillUiState.Empty)
    val responseEducation: MutableState<EducationUiState> = mutableStateOf(EducationUiState.Empty)

    private val _reviewUserJoinResult = MutableLiveData<ReviewUserJoinResults>()
    val reviewUserJoinResult: LiveData<ReviewUserJoinResults> = _reviewUserJoinResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun getProfileOther(userId: String, projectId: String) = viewModelScope.launch {
        projectRepo.getProfileUserJoin(userId, projectId)
            .onStart {
                response.value = ProfileUiState.Loading
            }.catch {
                response.value = ProfileUiState.Failure(it)
            }.collect {
                response.value = ProfileUiState.Success(it)
            }
    }

    fun getExperience(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherExperience(2, userId)
            .onStart {
                responseExperience.value = ExperienceUiState.Loading
            }.catch {
                responseExperience.value = ExperienceUiState.Failure(it)
            }.collect {
                responseExperience.value = ExperienceUiState.Success(it)
            }
    }

    fun getCertification(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherCertification(2, userId)
            .onStart {
                responseCertification.value = CertificationUiState.Loading
            }.catch {
                responseCertification.value = CertificationUiState.Failure(it)
            }.collect {
                responseCertification.value = CertificationUiState.Success(it)
            }
    }

    fun getSkill(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherSkill(2, userId)
            .onStart {
                responseSkill.value = SkillUiState.Loading
            }.catch {
                responseSkill.value = SkillUiState.Failure(it)
            }.collect {
                responseSkill.value = SkillUiState.Success(it)
            }
    }

    fun getEducation(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherEducation(2, userId)
            .onStart {
                responseEducation.value = EducationUiState.Loading
            }.catch {
                responseEducation.value = EducationUiState.Failure(it)
            }.collect {
                responseEducation.value = EducationUiState.Success(it)
            }
    }

    fun reviewUserJoin(userId: String, projectId: String, review: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = authPreferences.getAuthToken()
                apiService.userJoinReview(
                    token = "Bearer $token",
                    userId = userId,
                    projectId = projectId,
                    review = review
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
                                _reviewUserJoinResult.value = ReviewUserJoinResults.Success(message)
                            } else {
                                _reviewUserJoinResult.value = ReviewUserJoinResults.Error(message)
                            }
                        } else {
                            _isLoading.value = false
                            _reviewUserJoinResult.value = ReviewUserJoinResults.Error(response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                        _isLoading.value = false
                        _reviewUserJoinResult.value = ReviewUserJoinResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                _reviewUserJoinResult.value = ReviewUserJoinResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }
}

sealed class ReviewUserJoinResults {
    data class Success(val message: String) : ReviewUserJoinResults()
    data class Error(val errorMessage: String) : ReviewUserJoinResults()
}