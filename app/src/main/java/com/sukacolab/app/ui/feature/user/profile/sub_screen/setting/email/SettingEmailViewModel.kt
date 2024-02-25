package com.sukacolab.app.ui.feature.user.profile.sub_screen.setting.email

import androidx.lifecycle.ViewModel
import com.sukacolab.app.di.ResourcesProvider
import com.sukacolab.app.util.form.MainForm

class SettingEmailViewModel(
    resourcesProvider: ResourcesProvider
) : ViewModel()
{
    var form = MainForm(resourcesProvider)

}