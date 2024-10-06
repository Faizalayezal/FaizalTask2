package com.example.faizaltask2.Screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.faizaltask2.LocalData.ItemViewModel

@Composable
fun NoteListScreen(vm: ItemViewModel) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        ItemListScreen(vm)

    }
}

@Composable
fun ItemListScreen(viewModel: ItemViewModel) {
    val items = viewModel.itemss.collectAsLazyPagingItems()
    var isLoading by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items.itemCount) { index ->

                items[index]?.let {
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
            // Loading indicator at the end of the list
            items.apply {
                isLoading = when {
                    loadState.append is LoadState.Loading -> {
                        true
                    }

                    loadState.refresh is LoadState.Loading -> {
                        true

                    }else->{
                        false
                    }

                }
            }
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }

    }
}