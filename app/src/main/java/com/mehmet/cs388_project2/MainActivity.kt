package com.mehmet.cs388_project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var items: List<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemNameEditText = findViewById<EditText>(R.id.inputName)
        val itemPriceEditText = findViewById<EditText>(R.id.inputPrice)
        val itemURLEditText = findViewById<EditText>(R.id.inputURL)

        // Lookup the RecyclerView in activity layout
        val itemsRv = findViewById<RecyclerView>(R.id.itemsRecyclerView)

        // Fetch the list of items
        val items = ItemFetcher.getItems()

        // Create adapter passing in the list of items
        val adapter = ItemAdapter(items)

        // Attach the adapter to the RecyclerView to populate items
        itemsRv.adapter = adapter

        // Set layout manager to position the items
        itemsRv.layoutManager = LinearLayoutManager(this)

        // Allow user to enter a new item using the Add Item button and text fields
        findViewById<Button>(R.id.button).setOnClickListener {
            val newItem = Item(itemNameEditText.text.toString(), itemPriceEditText.text.toString(), itemURLEditText.text.toString())
            ItemFetcher.addItem(newItem)
            adapter.notifyDataSetChanged()

            // Clear the EditText fields
            itemNameEditText.setText("")
            itemPriceEditText.setText("")
            itemURLEditText.setText("")
        }



    }
}