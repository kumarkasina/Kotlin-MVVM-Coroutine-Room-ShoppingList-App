package com.example.shoppinglist.ui.ShoppingList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.data.adapters.ShoppingAdapter
import com.example.shoppinglist.data.db.ShoppingDatabase
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.data.repository.ShoppingRepository
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository= ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java )

        val adapter= ShoppingAdapter(listOf(),viewModel)
        rvshoppingItem.layoutManager = LinearLayoutManager(this)
        rvshoppingItem.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.itemlist=it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingDialog(this,object :AddDialogListener{

                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)

                }
            }).show()
        }



    }
}
