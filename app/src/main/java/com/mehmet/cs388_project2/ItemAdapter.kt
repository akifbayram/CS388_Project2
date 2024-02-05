package com.mehmet.cs388_project2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.itemNameTextView)
        val itemPriceTextView: TextView = itemView.findViewById(R.id.itemPriceTextView)
        val itemURLTextView: TextView = itemView.findViewById(R.id.itemURLTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemNameTextView.text = item.name
        holder.itemPriceTextView.text = item.price
        holder.itemURLTextView.text = item.url

        // Add dollar sign to price and decimals if not present
        if (!holder.itemPriceTextView.text.contains("$")) {
            holder.itemPriceTextView.text = "$" + holder.itemPriceTextView.text
        }
        if (!holder.itemPriceTextView.text.contains(".")) {
            holder.itemPriceTextView.text = holder.itemPriceTextView.text.toString() + ".00"
        }
        // Make sure there are two numbers after the decimal point
        if (holder.itemPriceTextView.text.split(".")[1].length == 1) {
            holder.itemPriceTextView.text = holder.itemPriceTextView.text.toString() + "0"
        }

        holder.itemView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + item.name, Toast.LENGTH_LONG).show()
            }
        }


        holder.itemView.setOnLongClickListener {
            ItemFetcher.removeItem(position)
            notifyDataSetChanged()
            Toast.makeText(it.context, "Item removed", Toast.LENGTH_LONG).show()
            true
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItem(position: Int) {
        items.toMutableList().removeAt(position)
        notifyDataSetChanged()
    }
}