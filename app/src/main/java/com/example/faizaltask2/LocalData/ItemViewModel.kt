package com.example.faizaltask2.LocalData

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.faizaltaskelluminati.LocalData.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val itemRepository: ItemRepository) : ViewModel() {


    private val _items = MutableStateFlow<List<Items>>(emptyList())
    val items: StateFlow<List<Items>> = _items.asStateFlow()


   /* init {
        viewModelScope.launch {
            itemRepository.getAllItems().collect { listOfItems ->
                _items.value = listOfItems
            }
        }
    }*/

    /*fun getNotePager(): Pager<Int, Items> {
        return Pager(config = PagingConfig(10)) {
            itemRepository.getNotePagingSource()
        }
    }*/
    val itemss: Flow<PagingData<Items>> = Pager(
        config = PagingConfig(pageSize = 5)
    ) {
        itemRepository.getNotePagingSource()
    }.flow.cachedIn(viewModelScope)

    fun deleteNote(noteEntity: Items) {
        viewModelScope.launch(Dispatchers.IO) {
            itemRepository.deleteItem(noteEntity)
        }
    }


    fun addItem(item: Items) = viewModelScope.launch { itemRepository.insertItem(item) }

    fun updateItem(item: Items) = viewModelScope.launch { itemRepository.updateItem(item) }

    fun deleteItem(item: Items) = viewModelScope.launch { itemRepository.deleteItem(item) }

}
