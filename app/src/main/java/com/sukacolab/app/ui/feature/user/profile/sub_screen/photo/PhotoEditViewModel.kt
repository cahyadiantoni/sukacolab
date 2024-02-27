package com.sukacolab.app.ui.feature.user.profile.sub_screen.photo

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.ui.feature.user.profile.ui_state.ProfileUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File

class PhotoEditViewModel(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    val response: MutableState<ProfileUiState> = mutableStateOf(ProfileUiState.Empty)

    fun editPhoto(image: File) = viewModelScope.launch {
        profileRepository.editPhoto(
            image = image
        )
            .onStart {
                response.value = ProfileUiState.Loading
            }.catch {
                response.value = ProfileUiState.Failure(it)
            }.collect {
                response.value = ProfileUiState.Success(it)
            }
        image.delete()
    }

}