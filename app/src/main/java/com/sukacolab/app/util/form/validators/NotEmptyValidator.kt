package com.sukacolab.app.util.form.validators

import com.sukacolab.app.util.form.Validator

class NotEmptyValidator<T>(errorText: String? = null) : Validator<T>(
    validate = {
        it != null
    },
    errorText = errorText ?: "This field should not be empty"
)