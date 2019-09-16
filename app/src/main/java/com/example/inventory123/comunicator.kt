package com.example.inventory123

interface comunicator {
    fun addItem(id:String, nombre: String, precio:String, cantidad:String)
    fun deleteItem(id:String)
    fun searchItem(id:String)
}