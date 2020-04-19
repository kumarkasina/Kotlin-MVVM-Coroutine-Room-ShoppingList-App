package com.example.shoppinglist.ui.ShoppingList

import com.example.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)
}