package com.example.books_filter_app.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Author::class, Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authorDao(): AuthorDao
}