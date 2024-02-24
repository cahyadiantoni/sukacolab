package com.sukacolab.app.ui.feature.user.profile.subScreen.skill

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.ui.feature.user.profile.ui_state.SkillUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SkillViewModel(
    private val profileRepo: ProfileRepository,
) : ViewModel() {
    val responseAllSkill: MutableState<SkillUiState> = mutableStateOf(SkillUiState.Empty)

    init {
        getAllSkill()
    }

    private fun getAllSkill() = viewModelScope.launch {
        profileRepo.getAllSkill()
            .onStart {
                responseAllSkill.value = SkillUiState.Loading
            }.catch {
                responseAllSkill.value = SkillUiState.Failure(it)
            }.collect {
                responseAllSkill.value = SkillUiState.Success(it)
            }
    }
}