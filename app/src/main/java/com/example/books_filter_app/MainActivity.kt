package com.example.books_filter_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val language = findViewById<TextInputLayout>(R.id.langTextInputView)
        val year = findViewById<TextInputLayout>(R.id.yearTextInputView)

        val button = findViewById<Button>(R.id.confirmButton)

        val resultQuantity = findViewById<TextView>(R.id.resultQuantity)
        val resultItems = findViewById<TextView>(R.id.resultItems)


        val myApplication = application as MyApplication
        val httpApiService = myApplication.httpApiService


        button.setOnClickListener{
            var year = year.editText?.text?.toString()
            var language = language.editText?.text?.toString()

            var maskedYear = if (year.isNullOrEmpty()) 0
            else Integer.valueOf(year)

            var maskedLang = if (language.isNullOrEmpty()) ""
            else language[0].uppercaseChar() + language.substring(1)
                .lowercase(Locale.getDefault())

            CoroutineScope(Dispatchers.IO).launch {
                val decodedJsonResult = httpApiService.getAllBooks()
                val books = decodedJsonResult.toList()

                val filteredBooks = books.filter { it.year >= maskedYear && it.language == maskedLang }

                val booksAsString = StringBuilder("")

                for (item in filteredBooks.take(3))
                    booksAsString.append("Result: "+item.title + "\n")

                withContext(Dispatchers.Main){
                    resultQuantity.text = "Results: "+filteredBooks.size.toString()
                    if(booksAsString.isEmpty()) {
                        resultItems.text = "Sorry, there is nothing for this request. " +
                                "Try other options."
                    } else {
                        resultItems.text = booksAsString
                    }
                }
            }
        }
    }
}