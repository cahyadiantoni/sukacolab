package com.sukacolab.app.ui.feature.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authPreferences: AuthPreferences,
    private val profileRepo: ProfileRepository
) : ViewModel() {
    val response: MutableState<ProfileUiState> = mutableStateOf(ProfileUiState.Empty)
    val id: Int?
        get() = (response.value as? ProfileUiState.Success)?.data?.id
    val email: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.email
    val fullName: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.fullName
    val faceShape: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.faceShape
    val isSubscribed: Boolean?
        get() = (response.value as? ProfileUiState.Success)?.data?.isSubscription
    val isPersonalityTest: Boolean?
        get() = (response.value as? ProfileUiState.Success)?.data?.isPersonalityTest
    val isFaceTest: Boolean?
        get() = (response.value as? ProfileUiState.Success)?.data?.isFaceTest
    val avatar: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.avatar
    val personalityType: String?
        get() = (response.value as? ProfileUiState.Success)?.data?.personalityType

    init {
        profileDetails()
    }

    private fun profileDetails() = viewModelScope.launch {
        profileRepo.profileDetails()
            .onStart {
                response.value = ProfileUiState.Loading
            }.catch {
                response.value = ProfileUiState.Failure(it)
            }.collect {
                response.value = ProfileUiState.Success(it)
            }
    }

    fun logout() {
       viewModelScope.launch {
           authPreferences.clearAuthToken()
       }
    }

}