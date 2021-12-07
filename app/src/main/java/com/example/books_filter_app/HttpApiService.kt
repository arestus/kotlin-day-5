package com.example.books_filter_app

import retrofit2.Call
import retrofit2.http.GET


interface HttpApiService {

    @GET("/books")
    suspend fun getAllBooks(): List<BookListItem>
}