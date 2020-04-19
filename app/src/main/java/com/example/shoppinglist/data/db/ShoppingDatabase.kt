package com.example.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase(){

    abstract fun getShoppingDao() : ShoppingDao

    companion object{
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val Lock= Any()

        operator fun invoke(context: Context)= instance
            ?: synchronized(Lock){

            instance
                ?: CreateDataBase(
                    context
                ).also { instance = it }
        }

        fun  CreateDataBase(context: Context)=
            Room.databaseBuilder(context.applicationContext,
                ShoppingDatabase::class.java,"ShoppinDB.db").build()
    }
}