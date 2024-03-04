package com.sukacolab.app.ui.feature.user.profile_other.sub_screen.certification

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.CertificationUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CertificationOtherViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseAllCertification: MutableState<CertificationUiState> = mutableStateOf(CertificationUiState.Empty)

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun getAllCertification(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherCertification(0, userId)
            .onStart {
                responseAllCertification.value = CertificationUiState.Loading
            }.catch {
                responseAllCertification.value = CertificationUiState.Failure(it)
            }.collect {
                responseAllCertification.value = CertificationUiState.Success(it)
            }
    }
}
