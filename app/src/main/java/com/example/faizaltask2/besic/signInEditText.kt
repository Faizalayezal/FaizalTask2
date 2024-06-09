package com.example.faizaltask2.besic

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInEditText(
    hint: String,
    selectedText: String? = null,
    onDataChanged: (Int) -> Unit,
) {
    val liveSelectedText = remember { mutableStateOf(selectedText ?: "") }
    onDataChanged.invoke(liveSelectedText.value.toIntOrNull() ?: 0)

    OutlinedTextField(
        placeholder = { Text(text = hint) },
        value = liveSelectedText.value,
        onValueChange = {
            liveSelectedText.value = it
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        textStyle = TextStyle(color = Color.Black),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            disabledTextColor = Color.Black,
            disabledPlaceholderColor = Color.Black,
            disabledBorderColor = Color.Transparent,

            ),
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = MaterialTheme.shapes.small.copy(CornerSize(10.dp))
            )
            .width(300.dp)
    )


}