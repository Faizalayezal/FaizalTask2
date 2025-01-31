package com.example.faizaltask2.LocalData

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.faizaltaskelluminati.LocalData.Items
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM Items")
    fun getAllItems(): Flow<List<Items>>

    @Query("SELECT * FROM Items")
    fun getAllNotesPaginated(): PagingSource<Int, Items>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Items)


    @Update
    suspend fun updateItem(item: Items)

    @Delete
    suspend fun deleteItem(item: Items)
}