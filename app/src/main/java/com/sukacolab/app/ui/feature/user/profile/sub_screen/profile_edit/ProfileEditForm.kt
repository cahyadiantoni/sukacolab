package com.sukacolab.app.ui.feature.user.profile.sub_screen.profile_edit

import androidx.compose.runtime.mutableStateOf
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.Role
import com.sukacolab.app.util.form.FieldState
import com.sukacolab.app.util.form.Form
import com.sukacolab.app.util.form.FormField
import com.sukacolab.app.util.form.validators.DateValidator
import com.sukacolab.app.util.form.validators.NotEmptyValidator
import java.util.Date

class ProfileEditForm(resourcesProvider: ResourcesProvider): Form() {
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
    val summary = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
        )
    )

    @FormField
    val whatsapp = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
        )
    )

    @FormField
    val linkedin = FieldState(
        state = mutableStateOf<String?>(null),
    )

    @FormField
    val github = FieldState(
        state = mutableStateOf<String?>(null),
    )

    @FormField
    val instagram = FieldState(
        state = mutableStateOf<String?>(null),
    )

}