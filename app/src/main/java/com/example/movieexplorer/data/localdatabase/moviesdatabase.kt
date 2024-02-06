package com.example.movieexplorer.data.localdatabase

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [MovieItemModel::class], version = 5)
abstract class MoviesRoomDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MoviesDao

    companion object {
        @Volatile
        private var database: MoviesRoomDatabase? = null

        fun getDatabase(context: Context):  MoviesRoomDatabase{
            return database ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesRoomDatabase::class.java,
                    "movies_database"
                ).fallbackToDestructiveMigration().build()
                database = instance
                instance
            }
        }

    }

}