package com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.add_experience

import androidx.compose.runtime.mutableStateOf
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.Role
import com.sukacolab.app.util.form.FieldState
import com.sukacolab.app.util.form.Form
import com.sukacolab.app.util.form.FormField
import com.sukacolab.app.util.form.validators.DateValidator
import com.sukacolab.app.util.form.validators.EmailValidator
import com.sukacolab.app.util.form.validators.IsEqualValidator
import com.sukacolab.app.util.form.validators.MinLengthValidator
import com.sukacolab.app.util.form.validators.NotEmptyValidator
import java.util.Date

class AddExperienceForm(resourcesProvider: ResourcesProvider): Form() {
    override fun self(): Form {
        return this
    }

    @FormField
    val title = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
        )
    )

    @FormField
    val company = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
        )
    )

    @FormField
    val role = FieldState(
        state = mutableStateOf<Role?>(null),
        options = mutableListOf(
            Role(name = "Full Time", nama = "Waktu penuh"),
            Role(name = "Part Time", nama = "Paruh waktu"),
            Role(name = "Enterpriser", nama = "Wiraswasta"),
            Role(name = "Freelance", nama = "Bekerja lepas"),
            Role(name = "Contract", nama = "Kontrak"),
            Role(name = "Internship", nama = "Magang"),
        ),
        optionItemFormatter = { "${it?.nama} (${it?.name})" },
        validators = mutableListOf(
            NotEmptyValidator()
        )
    )

    @FormField
    val startDate = FieldState(
        state = mutableStateOf<Date?>(null),
        validators = mutableListOf(
            NotEmptyValidator()
        )
    )

    @FormField
    val isNow = FieldState(
        state = mutableStateOf<Boolean?>(false),
    )

    @FormField
    val endDate = FieldState(
        state = mutableStateOf<Date?>(null),
        isVisible = { isNow.state.value == false },
        validators = mutableListOf(
            NotEmptyValidator(),
            DateValidator(
                minDateTime = {startDate.state.value?.time ?: 0},
                errorText = "Tanggal ini harus setelah tanggal mulai."
            )
        )
    )

}