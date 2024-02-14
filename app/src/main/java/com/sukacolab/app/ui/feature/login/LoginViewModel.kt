package com.sukacolab.app.ui.feature.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.request.LoginRequest
import com.sukacolab.app.data.source.network.response.LoginResponse
import com.sukacolab.app.domain.model.TextFieldState
import com.sukacolab.app.ui.common.UiEvents
import com.sukacolab.app.ui.feature.login.model.AuthState
import com.sukacolab.app.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
    ) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginApiResults>()
    val loginResult: LiveData<LoginApiResults> = _loginResult
    private var _loginState  = mutableStateOf(AuthState())
    val loginState: State<AuthState> = _loginState

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    fun setEmail(value:String){
        _emailState.value = emailState.value.copy(text = value)
    }

    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    fun setPassword(value:String){
        _passwordState.value = passwordState.value.copy(text = value)
    }

    private val _navigateToHome = mutableStateOf(false)
    val navigateToHome: State<Boolean> = _navigateToHome

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading


    fun loginNew(email: String, password: String, role: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val request = LoginRequest(email, password, role)

                apiService.login(request).enqueue(object: Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>,
                    ) {
                       if (response.isSuccessful) {
                           _isLoading.value = false
                           val responseBody = response.body()?.data
                           val userId = responseBody?.userId
                           val token = responseBody?.token

                           if (userId != null && token != null) {
                               viewModelScope.launch {
                                   authPreferences.saveAuthToken(token)
                                   authPreferences.saveAuthId(userId.toString())
                               }
                               // Update the login result with success
                               _loginResult.value = LoginApiResults.Success(userId, token)
                               viewModelScope.launch {
                                   _eventFlow.emit(
                                       UiEvents.NavigateEvent(Screen.Home.route)
                                   )
                               }
                               _navigateToHome.value = true
                           } else {
                               _loginResult.value = LoginApiResults.Error("Invalid response")
                               clearEmail()
                               clearPassword()
                           }
                       } else {
                           _isLoading.value = false
                           _loginResult.value = LoginApiResults.Error(response.message())
                           viewModelScope.launch {
                                 _eventFlow.emit(
                                      UiEvents.SnackbarEvent(response.message())
                                 )
                           }
                           clearEmail()
                           clearPassword()
                       }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        _isLoading.value = false
                        _loginResult.value = LoginApiResults.Error(t.localizedMessage ?: "Unknown error occurred")
                    }

                })
            } catch (e: Exception) {
                // Update the login result with error
                _loginResult.value = LoginApiResults.Error(e.localizedMessage ?: "Unknown error occurred")
            }
        }
    }

    fun clearEmail() {
        setEmail("")
    }

    fun clearPassword() {
        setPassword("")
    }

}



sealed class LoginApiResults {
    data class Success(val userId: Int, val token: String) : LoginApiResults()
    data class Error(val errorMessage: String) : LoginApiResults()
}