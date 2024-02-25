package com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.email

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sukacolab.app.di.ResourcesProvider

class SettingEmailViewModel(
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = MainForm(resourcesProvider)

    fun validate() {
        form.validate(true)
        form.logRawValue()
        Log.d("MainViewModel", "Submit (form is valid: ${form.isValid})")
    }
}