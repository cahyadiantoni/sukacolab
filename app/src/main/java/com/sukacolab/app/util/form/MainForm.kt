package com.sukacolab.app.util.form

import androidx.compose.runtime.mutableStateOf
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.util.form.validators.DateValidator
import com.sukacolab.app.util.form.validators.EmailValidator
import com.sukacolab.app.util.form.validators.IsEqualValidator
import com.sukacolab.app.util.form.validators.MinLengthValidator
import com.sukacolab.app.util.form.validators.NotEmptyValidator
import java.util.Date

class MainForm(resourcesProvider: ResourcesProvider): Form() {
    override fun self(): Form {
        return this
    }

    @FormField
    val name = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            MinLengthValidator(
                minLength = 3,
                errorText = "Input ini tidak sesuai dengan persyaratan panjang minimum."
            )
        )
    )

    @FormField
    val lastName = FieldState(
        state = mutableStateOf<String?>(null)
    )

    @FormField
    val oldPassword = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            MinLengthValidator(
                minLength = 8,
                errorText = "Input ini tidak sesuai dengan persyaratan panjang minimum."
            )
        )
    )

    @FormField
    val password = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            MinLengthValidator(
                minLength = 8,
                errorText = "Input ini tidak sesuai dengan persyaratan panjang minimum."
            )
        )
    )

    @FormField
    val passwordConfirm = FieldState(
        state = mutableStateOf<String?>(null),
        isVisible = { password.state.value != null && password.state.value!!.isNotEmpty()  },
        validators = mutableListOf(
            IsEqualValidator({ password.state.value })
        )
    )

    @FormField
    val email = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            EmailValidator()
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
    val endDate = FieldState(
        state = mutableStateOf<Date?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            DateValidator(
                minDateTime = {startDate.state.value?.time ?: 0},
                errorText = "Tanggal ini harus setelah tanggal mulai."
            )
        )
    )

    @FormField
    val agreeWithTerms = FieldState(
        state = mutableStateOf<Boolean?>(null),
        validators = mutableListOf(
            IsEqualValidator({ true })
        )
    )
}