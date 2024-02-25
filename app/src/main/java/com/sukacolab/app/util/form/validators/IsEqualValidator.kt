package com.sukacolab.app.util.form.validators

import com.sukacolab.app.util.form.Validator

class IsEqualValidator<T>(expectedValue: () -> T, errorText: String? = null) : Validator<T>(
    validate = {
        it == expectedValue()
    },
    errorText = errorText ?: "This field's value is not as expected."
)