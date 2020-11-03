package com.cruckman900.mymarvelcomiccollection.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WishlistDAO {
    @Insert
    fun saveToWishlist(comic: WishlistEntity)

    @Query(value = "SELECT * FROM wishlist ORDER BY title, year, issueNumber")
    fun getWishlist() : List<WishlistEntity>

    @Delete
    fun deleteEntry(comic: WishlistEntity)
}