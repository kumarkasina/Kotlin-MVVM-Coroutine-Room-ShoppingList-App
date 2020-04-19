package com.example.shoppinglist.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.ShoppingList.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingAdapter(var itemlist:List<ShoppingItem>,private var viewModel: ShoppingViewModel):RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHoder>() {


    inner class  ShoppingViewHoder(itemview: View):RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHoder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.shopping_item,parent,false)
        return ShoppingViewHoder(view);
    }

    override fun getItemCount(): Int {
       return itemlist.size;
    }

    override fun onBindViewHolder(holder: ShoppingViewHoder, position: Int) {
        val curshopingItem = itemlist[position];

        holder.itemView.textView.text=curshopingItem.name
        holder.itemView.textView2.text="${curshopingItem.amount}"
        holder.itemView.imageView4.setOnClickListener { viewModel.delete(curshopingItem) }
        holder.itemView.imageView2.setOnClickListener {
            curshopingItem.amount++
            viewModel.upsert(curshopingItem) }
        holder.itemView.imageView3.setOnClickListener {

            if(curshopingItem.amount>0)
                curshopingItem.amount--

            viewModel.upsert(curshopingItem) }

    }

}