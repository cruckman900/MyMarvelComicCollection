package com.cruckman900.mymarvelcomiccollection.model

import androidx.room.*

@Dao
interface WishlistDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveToWishlist(comic: WishlistEntity)

    @Query(value = "SELECT * FROM wishlist ORDER BY title, year, issueNumber")
    fun getWishlist() : List<WishlistEntity>

    @Delete
    fun deleteEntry(comic: WishlistEntity)

    @Query("DELETE FROM wishlist")
    fun nukeWishlist()
}