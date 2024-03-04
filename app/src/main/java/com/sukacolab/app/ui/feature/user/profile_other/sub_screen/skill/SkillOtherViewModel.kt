package com.sukacolab.app.ui.feature.user.profile_other.sub_screen.skill

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.data.repository.ProjectRepository
import com.sukacolab.app.data.source.local.AuthPreferences
import com.sukacolab.app.data.source.network.ApiService
import com.sukacolab.app.data.source.network.response.BaseResponse
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailSkillUiState
import com.sukacolab.app.ui.feature.user.profile_other.ui_state.SkillUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SkillOtherViewModel(
    private val projectRepo: ProjectRepository,
) : ViewModel() {
    val responseAllSkill: MutableState<SkillUiState> = mutableStateOf(SkillUiState.Empty)

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun getAllSkill(userId: Int) = viewModelScope.launch {
        projectRepo.getOtherSkill(0, userId)
            .onStart {
                responseAllSkill.value = SkillUiState.Loading
            }.catch {
                responseAllSkill.value = SkillUiState.Failure(it)
            }.collect {
                responseAllSkill.value = SkillUiState.Success(it)
            }
    }
}