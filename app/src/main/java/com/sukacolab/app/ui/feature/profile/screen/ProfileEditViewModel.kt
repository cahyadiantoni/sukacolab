package com.sukacolab.app.ui.feature.profile.screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.ui.feature.profile.uiState.ProfileUiState
import com.sukacolab.app.ui.feature.register.model.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileEditViewModel(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences
) : ViewModel() {
    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Empty)
    val uiState: StateFlow<ProfileUiState> = _uiState
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    private val _responseMessage = MutableStateFlow<String?>(null)
    val responseMessage: StateFlow<String?> = _responseMessage

    fun editProfile(request: RegisterRequest) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val token = authPreferences.getAuthToken()
                val userId = authPreferences.getAuthId()

                val response = apiService.editProfile(
                    token = "Bearer $token",
                    userId = userId.toString(),
                    request = request
                )

                _isLoading.value = false
                if (response.data != null) {
                    // Registration successful
                    val message = response.message
                    // Handle the success response accordingly
                    _responseMessage.value = "Registered: $message"
                    Log.d("neoTag", "Registered: $message")
                } else {
                    // Response body is null
                    _responseMessage.value = "Response body is null"
                    Log.d("neoTag", "Response body is null")
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _responseMessage.value = "Registration failed: ${e.message}"
                Log.d("neoTag", "Registration failed: ${e.message}")
            }
        }
    }

    fun clearResponseMessage() {
        _responseMessage.value = null
    }
}