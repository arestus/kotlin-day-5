package com.example.books_filter_app


import com.example.books_filter_app.data.Author
import retrofit2.http.GET


interface HttpApiService {

    @GET("/books")
    suspend fun getAllBooks(): List<BookListItem>

//    @GET("/books")
//    suspend fun getAuthors(): List<Author>
}