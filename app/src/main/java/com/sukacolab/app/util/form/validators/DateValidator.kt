package com.sukacolab.app.util.form.validators

import com.sukacolab.app.util.form.Validator
import java.util.*

class DateValidator(minDateTime: () -> Long, errorText: String? = null) : Validator<Date?>(
    validate = {
        (it?.time ?: -1) >= minDateTime()
    },
    errorText = errorText ?: "This field is not valid."
)