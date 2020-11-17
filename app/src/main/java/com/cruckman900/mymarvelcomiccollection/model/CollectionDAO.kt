package com.cruckman900.mymarvelcomiccollection.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CollectionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveToCollection(comic: CollectionEntity)

    @Query(value = "SELECT * FROM collection ORDER BY title, year, issueNumber")
    fun getCollection(): List<CollectionEntity>

    @Delete
    fun deleteEntry(comic: CollectionEntity)

    @Query("DELETE from collection")
    fun nukeCollection()
}