package com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.password

import androidx.lifecycle.ViewModel
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.util.form.MainForm

class SettingPasswordViewModel(
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = SettingPasswordForm(resourcesProvider)


}