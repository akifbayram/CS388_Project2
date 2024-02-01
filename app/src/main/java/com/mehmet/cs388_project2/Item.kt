package com.mehmet.cs388_project2

class Item (
    val name: String,
    val price: String,
    val url: String) {
}

class ItemFetcher {
    companion object {
        private val items: MutableList<Item> = mutableListOf()

        fun addItem(item: Item) {
            items.add(item)
        }

        fun getItems(): List<Item> {
            return items
        }
    }
}