package com.sukacolab.app.util.form

abstract class Validator<T>(
    val validate: (s: T?) -> Boolean,
    val errorText: String
)
