package com.sukacolab.app.ui.feature.user.profile.ui_state

import com.sukacolab.app.data.source.network.response.Skill

sealed class SkillUiState {
    class Success(val data: List<Skill>) : SkillUiState()
    class Failure(val error: Throwable) : SkillUiState()
    object Loading : SkillUiState()
    object Empty : SkillUiState()
}