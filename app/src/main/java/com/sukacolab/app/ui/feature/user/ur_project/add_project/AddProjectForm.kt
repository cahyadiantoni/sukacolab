package com.sukacolab.app.ui.feature.user.ur_project.add_project

import androidx.compose.runtime.mutableStateOf
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.ui.feature.user.profile.sub_screen.experience.Role
import com.sukacolab.app.util.form.FieldState
import com.sukacolab.app.util.form.Form
import com.sukacolab.app.util.form.FormField
import com.sukacolab.app.util.form.validators.DateValidator
import com.sukacolab.app.util.form.validators.NotEmptyValidator
import java.util.Date

class AddProjectForm(resourcesProvider: ResourcesProvider): Form() {
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
    val position = FieldState(
        state = mutableStateOf<Position?>(null),
        options = mutableListOf(
            Position(name = "Project Manager"),
            Position(name = "Quality Assurance"),
            Position(name = "UI/UX Designer"),
            Position(name = "DevOps Engineer"),
            Position(name = "Android Developer"),
            Position(name = "Web Developer"),
            Position(name = "Database Engineer"),
            Position(name = "IOS Developer"),
            Position(name = "Other"),
        ),
        optionItemFormatter = { "${it?.name}" },
        validators = mutableListOf(
            NotEmptyValidator()
        )
    )

    @FormField
    val otherPosition = FieldState(
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