package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.dao.BookmarkDao
import com.example.data.db.entity.Bookmark

@Database(entities = [Bookmark::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(Database::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-db"
                    ).build()
                }
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }
    }
}