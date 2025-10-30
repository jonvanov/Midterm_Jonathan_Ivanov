package com.example.midterm_jonathan_ivanov

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

// simple in-memory store to keep numbers generated (for History screen)
object DataStore {
    val history = linkedSetOf<Int>()
}

class MainActivity : AppCompatActivity() {
    private lateinit var numberInput: EditText
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val rows = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberInput = findViewById(R.id.numberInput)
        listView = findViewById(R.id.tableListView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, rows)
        listView.adapter = adapter

        findViewById<Button>(R.id.generateButton).setOnClickListener {
            val txt = numberInput.text.toString()
            if (txt.isBlank()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val n = txt.toInt()
            generateTable(n)
            DataStore.history.add(n)
        }

        listView.setOnItemClickListener { _, _, pos, _ ->
            val item = rows[pos]
            AlertDialog.Builder(this)
                .setTitle("Delete Row")
                .setMessage("Delete $item?")
                .setPositiveButton("Yes") { _, _ ->
                    rows.removeAt(pos)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Deleted: $item", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No", null)
                .show()
        }

        findViewById<Button>(R.id.historyButton).setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }

    private fun generateTable(n: Int) {
        rows.clear()
        for (i in 1..10) rows.add("$n × $i = ${n * i}")
        adapter.notifyDataSetChanged()
    }
}
