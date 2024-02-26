package com.sukacolab.app.ui.feature.user.profile.ui_state

import com.sukacolab.app.data.source.network.response.DetailSkill

sealed class DetailSkillUiState {
    class Success(val data: DetailSkill) : DetailSkillUiState()
    class Failure(val error: Throwable) : DetailSkillUiState()
    object Loading : DetailSkillUiState()
    object Empty : DetailSkillUiState()
}