package com.cruckman900.mymarvelcomiccollection.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist")
class WishlistEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "year")
    var year: Int = 0

    @ColumnInfo(name = "issueNumber")
    var issueNumber: Int = 0

    @ColumnInfo (name = "imageURL")
    var imageURL: String = ""
}