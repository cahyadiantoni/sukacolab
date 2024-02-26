package com.sukacolab.app.ui.feature.user.profile.sub_screen.skill

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukacolab.app.data.repository.ProfileRepository
import com.sukacolab.app.ui.feature.user.profile.ui_state.DetailSkillUiState
import com.sukacolab.app.ui.feature.user.profile.ui_state.SkillUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SkillViewModel(
    private val profileRepo: ProfileRepository,
) : ViewModel() {
    val responseAllSkill: MutableState<SkillUiState> = mutableStateOf(SkillUiState.Empty)
    val responseDetailSkill: MutableState<DetailSkillUiState> = mutableStateOf(
        DetailSkillUiState.Empty)

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

    fun getDetailSkill(id: String) = viewModelScope.launch {
        profileRepo.getDetailSkill(id)
            .onStart {
                responseDetailSkill.value = DetailSkillUiState.Loading
            }.catch {
                responseDetailSkill.value = DetailSkillUiState.Failure(it)
            }.collect {
                responseDetailSkill.value = DetailSkillUiState.Success(it)
            }
    }
}