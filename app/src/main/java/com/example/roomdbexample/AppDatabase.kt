package com.example.roomdbexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Student :: class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun studentDao(): StudentDao

    // to make sure only one instance of db is created in application
    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" //any name can be given
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}