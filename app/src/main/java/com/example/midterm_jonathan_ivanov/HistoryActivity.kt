package com.example.midterm_jonathan_ivanov

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val list = findViewById<ListView>(R.id.historyListView)
        val numbers = DataStore.history.toList()
        list.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
    }
}
