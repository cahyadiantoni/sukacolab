package com.sukacolab.app.ui.feature.user.profile.sub_screen.skill.edit_skill

import androidx.compose.runtime.mutableStateOf
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.util.form.FieldState
import com.sukacolab.app.util.form.Form
import com.sukacolab.app.util.form.FormField
import com.sukacolab.app.util.form.validators.NotEmptyValidator

class EditSkillForm(resourcesProvider: ResourcesProvider): Form() {
    override fun self(): Form {
        return this
    }

    @FormField
    val name = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
        )
    )

    @FormField
    val description = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
        )
    )

}