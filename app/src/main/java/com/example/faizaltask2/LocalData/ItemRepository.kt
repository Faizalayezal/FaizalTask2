package com.example.faizaltask2.LocalData

import androidx.paging.PagingSource
import com.example.faizaltaskelluminati.LocalData.Items
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {

    fun getAllItems(): Flow<List<Items>> = itemDao.getAllItems()

    suspend fun insertItem(item: Items) = itemDao.insertItem(item)

    suspend fun updateItem(item: Items) = itemDao.updateItem(item)

    suspend fun deleteItem(item: Items) = itemDao.deleteItem(item)

    fun getNotePagingSource(): PagingSource<Int, Items> {
        return itemDao.getAllNotesPaginated()
    }
}
