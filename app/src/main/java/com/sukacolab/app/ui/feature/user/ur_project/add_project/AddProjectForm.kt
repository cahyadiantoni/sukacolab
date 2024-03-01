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
        ),
        isVisible = { position.state.value?.name == "Other" },
    )

    @FormField
    val isRemote = FieldState(
        state = mutableStateOf<Boolean?>(false),
    )

    @FormField
    val location = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
        ),
        isVisible = { isRemote.state.value == false },
    )

    @FormField
    val tipe = FieldState(
        state = mutableStateOf<Tipe?>(null),
        options = mutableListOf(
            Tipe(name = "Loker"),
            Tipe(name = "Portofolio"),
            Tipe(name = "Kompetisi"),
            Tipe(name = "Other"),
        ),
        optionItemFormatter = { "${it?.name}" },
        validators = mutableListOf(
            NotEmptyValidator()
        )
    )

    @FormField
    val otherTipe = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
        ),
        isVisible = { tipe.state.value?.name == "Other" },
    )

    @FormField
    val isPaid = FieldState(
        state = mutableStateOf<Boolean?>(false),
    )

    @FormField
    val time = FieldState(
        state = mutableStateOf<Time?>(null),
        options = mutableListOf(
            Time(name = "Full Time", nama = "Waktu penuh"),
            Time(name = "Part Time", nama = "Paruh waktu"),
            Time(name = "Enterpriser", nama = "Wiraswasta"),
            Time(name = "Freelance", nama = "Bekerja lepas"),
            Time(name = "Contract", nama = "Kontrak"),
            Time(name = "Internship", nama = "Magang"),
        ),
        optionItemFormatter = { "${it?.nama} (${it?.name})" },
        validators = mutableListOf(
            NotEmptyValidator()
        )
    )

    @FormField
    val description = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
        )
    )

    @FormField
    val requirements = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
        )
    )
}