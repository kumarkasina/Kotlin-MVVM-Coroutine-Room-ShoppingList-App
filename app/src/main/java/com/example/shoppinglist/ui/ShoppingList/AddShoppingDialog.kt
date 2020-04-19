package com.example.shoppinglist.ui.ShoppingList

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingDialog(context: Context,var dialogListener: AddDialogListener):AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        tvadd.setOnClickListener {
            var name = etname.text.toString()
            var amount = etamount.text.toString()

            if(name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context,"Please enter all information",Toast.LENGTH_LONG).show();

                return@setOnClickListener
            }

            var item= ShoppingItem(name,amount.toInt())
            dialogListener.onAddButtonClicked(item);
            dismiss()


        }

        tvcancel.setOnClickListener {
            cancel()
        }
    }


}