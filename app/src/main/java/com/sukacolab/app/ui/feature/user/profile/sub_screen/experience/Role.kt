package com.sukacolab.app.ui.feature.user.profile.sub_screen.experience

import com.sukacolab.app.ui.component.fields.PickerValue

data class Role(
    val name: String,
    val nama: String
): PickerValue() {
    override fun searchFilter(query: String): Boolean {
        val lowercaseQuery = query.lowercase()
        return this.nama.lowercase().startsWith(lowercaseQuery) || this.name.lowercase().startsWith(lowercaseQuery)
    }
}