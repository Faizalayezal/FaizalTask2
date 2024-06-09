package com.example.faizaltask2.Screen


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.faizaltask2.LocalData.ItemViewModel
import com.example.faizaltask2.besic.SignInEditText
import com.example.faizaltaskelluminati.LocalData.Items
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CalculationScreen(viewModel: ItemViewModel, onClick: () -> Unit) {

    val scope = rememberCoroutineScope()

    val value1 = remember {
        mutableIntStateOf(0)
    }

    val value2 = remember {
        mutableIntStateOf(0)
    }
    val sign = remember {
        mutableStateOf("")
    }
    val sum = remember {
        mutableStateOf(0)
    }

    val showValues by remember {
        derivedStateOf {
            "${value1.value} ${sign.value} ${value2.value}"
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        SignInEditText(
            "Enter value",
            onDataChanged = { it ->
                value1.value = it

                Log.d("TAG", "CalculationScreen: " + value1)

            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        SignInEditText(
            "Enter value",
            onDataChanged = { it ->

                value2.value = it

            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        DropDown() {
            Log.d("TAG", "CalculationScreen: " + it)

            sign.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .height(50.dp),
            onClick = {
                scope.launch {
                    launch {
                        sum.value = when (sign.value) {
                            "+" -> value1.value + value2.value
                            "-" -> value1.value - value2.value
                            "*" -> value1.value * value2.value
                            "/" -> {
                                if (value2.value == 0) {
                                    throw IllegalArgumentException("Division by zero")
                                } else {
                                    value1.value / value2.value
                                }
                            }

                            else -> {
                                throw IllegalArgumentException("Value not found")

                            }
                        }
                        delay(2000)
                    }.join()
                    launch {
                        val itemData = Items(
                            firstValue = value1.value.toString(),
                            secondValue = value2.value.toString(),
                            sign = sign.value,
                            total = sum.value.toString()
                        )
                        viewModel.addItem(itemData)
                    }


                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Black,
            ),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Calculate", color = White)
        }

        Text(
            modifier = Modifier
                .height(36.dp),
            text = "$showValues = ${sum.value}",
            fontSize = 25.sp,
            color = Black,
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .height(50.dp),
            onClick = {
                onClick.invoke()

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Black,
            ),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Show Data", color = White)
        }


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
inline fun DropDown(onDataChanged: (String) -> Unit) {
    val list = listOf("+", "-", "*", "/")

    var selectedText by remember {

        mutableStateOf(list[0])
    }

    onDataChanged.invoke(selectedText)
    var isExpanded by remember {

        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded })
        {
            TextField(
                // The `menuAnchor` modifier must be passed to the text field to handle
                // expanding/collapsing the menu on click. A read-only text field has
                // the anchor type `PrimaryNotEditable`.
                modifier = Modifier.menuAnchor(),
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                label = { Text("Sign") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                list.forEachIndexed { index, text ->
                    DropdownMenuItem(text = { Text(text = text) }, onClick = {
                        selectedText = list[index]
                        isExpanded = false
                    },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }

            }


        }

    }


}
