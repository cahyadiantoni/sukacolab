package com.sukacolab.app.ui.feature.user.profile.sub_screen.about

import androidx.compose.runtime.mutableStateOf
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.util.form.FieldState
import com.sukacolab.app.util.form.Form
import com.sukacolab.app.util.form.FormField
import com.sukacolab.app.util.form.validators.MinLengthValidator
import com.sukacolab.app.util.form.validators.NotEmptyValidator

class EditAboutForm(resourcesProvider: ResourcesProvider): Form() {
    override fun self(): Form {
        return this
    }

    @FormField
    val about = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
        )
    )
}