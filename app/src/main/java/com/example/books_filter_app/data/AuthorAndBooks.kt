package com.example.books_filter_app.data

import androidx.room.Embedded
import androidx.room.Relation

data class AuthorAndBooks(
    @Embedded val author: Author,
            @Relation(
                parentColumn = "author",
                entityColumn = "author"
            )
            val books: List<Book>
)