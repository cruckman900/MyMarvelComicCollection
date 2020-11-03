package com.cruckman900.mymarvelcomiccollection.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [(CollectionEntity::class), (WishlistEntity::class)], version = 1)
abstract class MMCCDB : RoomDatabase() {
    abstract fun collectionDao(): CollectionDAO
    abstract fun wishlistDao(): WishlistDAO

    companion object {
        @Volatile
        private var INSTANCE: MMCCDB? = null

        fun createInstance(context: Context): MMCCDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            else {
                synchronized(this) {
                    if (tempInstance != null) return tempInstance
                    else {
                        val instance = Room.databaseBuilder(
                            context,
                            MMCCDB::class.java,
                            "mmcc_db"
                        ).build()
                        INSTANCE = instance
                        return instance
                    }
                }
            }
        }
    }
}