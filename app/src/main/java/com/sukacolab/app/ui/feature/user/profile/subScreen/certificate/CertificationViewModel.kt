package com.sukacolab.app.ui.feature.user.profile.subScreen.certificate

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.ui.feature.user.profile.ui_state.CertificationUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CertificationViewModel(
    private val profileRepo: ProfileRepository,
) : ViewModel() {
    val responseAllCertification: MutableState<CertificationUiState> = mutableStateOf(CertificationUiState.Empty)

    init {
        getAllCertification()
    }

    private fun getAllCertification() = viewModelScope.launch {
        profileRepo.getAllCertification()
            .onStart {
                responseAllCertification.value = CertificationUiState.Loading
            }.catch {
                responseAllCertification.value = CertificationUiState.Failure(it)
            }.collect {
                responseAllCertification.value = CertificationUiState.Success(it)
            }
    }
}