package com.example.faizaltaskelluminati.LocalData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Items(

    val firstValue:String?,
    val secondValue:String?,
    val sign:String?,
    val total:String?,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
)

