package com.sukacolab.app.util.form.validators

import com.sukacolab.app.util.form.Validator

class MinLengthValidator(minLength: Int, errorText: String? = null) : Validator<String?>(
    validate = {
        (it?.length ?: -1) >= minLength
    },
    errorText = errorText ?: "This field is too short"
)