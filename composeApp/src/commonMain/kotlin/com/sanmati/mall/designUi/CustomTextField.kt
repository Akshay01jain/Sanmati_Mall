package com.sanmati.mall.designUi

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.sanmati.mall.theme.OnSurface
import com.sanmati.mall.theme.OnSurfaceVariant
import com.sanmati.mall.theme.Primary
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    enabled: Boolean = true,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, style = MaterialTheme.typography.bodyMedium) },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            singleLine = singleLine,
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = if (isError) MaterialTheme.colorScheme.error else Primary,
                unfocusedIndicatorColor = if (isError) MaterialTheme.colorScheme.error else OnSurfaceVariant,
                cursorColor = Primary,
                focusedTextColor = OnSurface,
                disabledTextColor = OnSurfaceVariant,
                errorCursorColor = MaterialTheme.colorScheme.error,
                errorLeadingIconColor = MaterialTheme.colorScheme.error,
                errorTrailingIconColor = MaterialTheme.colorScheme.error,
                errorLabelColor = MaterialTheme.colorScheme.error,
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
        if (isError && !errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}


@Composable
fun FilledTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String = "",
    placeholderText: String = "",
    labelText: String = "",
    showLabel: Boolean = true,
    isError: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    labelTextStyle: TextStyle = TextStyle.Default,
    placeholderTextStyle: TextStyle = TextStyle.Default,
    keyBoardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyBoardActions: KeyboardActions = KeyboardActions.Default,
    errorText: String = "",
    errorStyle: ErrorStyle = ErrorStyle(),
    enabled: Boolean = true,
    singleLine: Boolean = false,
    backgroundStyle: BackgroundStyle = BackgroundStyle(),
    borderStyle: BorderStyle = BorderStyle(),
    cursorStyle: CursorStyle = CursorStyle(),
    shape: Shape = CircleShape,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    labelPaddingValues: PaddingValues = PaddingValues(start = 16.dp),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValidate: (text: String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement
            .spacedBy(10.dp)
    ) {
        if (showLabel) {
            Text(
                modifier = Modifier
                    .padding(labelPaddingValues),
                text = labelText,
                style = if (isError) {
                    labelTextStyle.copy(
                        color = errorStyle.errorLabelColor
                    )
                } else {
                    labelTextStyle
                }
            )
        }

        OutlinedTextField(
            modifier = modifier,
            value = text,
            enabled = enabled,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundStyle.focusedBackgroundColor,
                unfocusedContainerColor = backgroundStyle.unfocusedBackgroundColor,
                disabledContainerColor = backgroundStyle.disabledBackgroundColor,
                errorContainerColor = errorStyle.errorContainerColor,
                focusedIndicatorColor = borderStyle.focusedBorderColor,
                unfocusedIndicatorColor = borderStyle.unfocusedBorderColor,
                disabledIndicatorColor = borderStyle.disabledBorderColor,
                errorIndicatorColor = errorStyle.errorBorderColor,
                errorLabelColor = errorStyle.errorLabelColor,
                cursorColor = cursorStyle.focusedCursorColor,
                errorCursorColor = errorStyle.errorCursorColor
            ),

            shape = shape,
            isError = isError,
            onValueChange = {
                onValidate(it)
            },
            keyboardOptions = keyBoardOptions,
            textStyle = textStyle,
            singleLine = singleLine,
            placeholder = {
                Text(
                    text = placeholderText,
                    style = placeholderTextStyle,
                )
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            keyboardActions = keyBoardActions,
            supportingText = {
                if (isError) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .size(errorStyle.errorIconSize),
                            imageVector = errorStyle.errorIconRes,
                            contentDescription = "Error Icon"
                        )

                        Text(
                            text = errorText,
                            style = errorStyle.errorTextStyle
                        )
                    }
                }
            }
        )
    }
}