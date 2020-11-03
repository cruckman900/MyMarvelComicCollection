package com.cruckman900.mymarvelcomiccollection.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CollectionDAO {
    @Insert
    fun saveToCollection(comic: CollectionEntity)

    @Query(value = "SELECT * FROM collection ORDER BY title, year, issueNumber")
    fun getCollection() : List<CollectionEntity>

    @Delete
    fun deleteEntry(comic: CollectionEntity)
}