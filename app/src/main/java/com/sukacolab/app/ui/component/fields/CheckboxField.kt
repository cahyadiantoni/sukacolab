package com.sukacolab.app.ui.component.fields

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.sukacolab.app.ui.component.form.CheckboxComponent
import com.sukacolab.app.util.form.Field
import com.sukacolab.app.util.form.FieldState
import com.sukacolab.app.util.form.Form

class CheckboxField(
    label: String,
    form: Form,
    modifier: Modifier? = Modifier,
    fieldState: FieldState<Boolean?>,
    isEnabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    formatter: ((raw: Boolean?) -> String)? = null,
    changed: ((v: Boolean?) -> Unit)? = null
) : Field<Boolean>(
    label = label,
    form = form,
    fieldState = fieldState,
    isEnabled = isEnabled,
    modifier = modifier,
    imeAction = imeAction,
    formatter = formatter,
    changed = changed
) {

    /**
     * Returns a composable representing the DateField / Picker for this field
     */
    @Composable
    override fun Field() {
        this.updateComposableValue()
        if (!fieldState.isVisible()) {
            return
        }
        CheckboxComponent(
            modifier = modifier ?: Modifier,
            checked = fieldState.state.value == true,
            onCheckedChange = {
                this.onChange(it, form)
            },
            label = label,
            hasError = fieldState.hasError(),
            errorText = fieldState.errorText
        )
    }
}