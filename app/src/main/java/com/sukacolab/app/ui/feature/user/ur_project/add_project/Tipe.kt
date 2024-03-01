package com.sukacolab.app.ui.feature.user.ur_project.add_project

import com.sukacolab.app.ui.component.fields.PickerValue

data class Tipe(
    val name: String
): PickerValue() {
    override fun searchFilter(query: String): Boolean {
        val lowercaseQuery = query.lowercase()
        return this.name.lowercase().startsWith(lowercaseQuery)
    }
}