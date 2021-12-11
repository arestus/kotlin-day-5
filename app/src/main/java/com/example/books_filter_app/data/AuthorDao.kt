package com.example.books_filter_app.data

import androidx.room.*
import com.example.books_filter_app.BookListItem


@Dao
interface AuthorDao {
    @Query("SELECT * FROM author_table")
    fun getAll(): List<Author>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAuthors(vararg authors: Author)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBooks(vararg books: Book)

    @Transaction
    @Query("SELECT * FROM author_table WHERE author = :author")
    suspend fun getAuthorWithBooks(author: String): List<AuthorAndBooks>
}
