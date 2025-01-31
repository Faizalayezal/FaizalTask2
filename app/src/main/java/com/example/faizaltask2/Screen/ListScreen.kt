package com.example.faizaltask2.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.faizaltask2.LocalData.ItemViewModel


@SuppressLint("UnrememberedMutableState", "RestrictedApi", "MutableCollectionMutableState")
@Composable
fun ListScreen(
    modifier: Modifier,
    viewModel: ItemViewModel,
    popBack: (() -> Unit)? = null,
) {

    val items = viewModel.items.collectAsState().value


    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 40.dp, top = 20.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 30.dp, bottom = 10.dp),
        ) {
            LazyColumn() {
                items(items) { it ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(10.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(
                                text = "${it.firstValue} ${it.sign} ${it.secondValue} = ${it.total}",
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                            )
                        }

                    }


                }


            }

        }

    }


}





