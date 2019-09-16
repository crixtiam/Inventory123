package com.example.inventory123

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory123.Peluches
import com.example.inventory123.R
import kotlinx.android.synthetic.main.fragment_add_item.view.*
import kotlinx.android.synthetic.main.peluche_item.view.*

class inventoryAdapter : RecyclerView.Adapter<inventoryAdapter.inventoryViewHolder> {


    private var listPeluches: MutableList<Peluches>? = null
    private var totalListPeluches : MutableList<Peluches> ?= null

    private var context : Context? = null

    constructor(listPeluches: MutableList<Peluches>,context: Context){
        this.listPeluches = listPeluches
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): inventoryViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.peluche_item,parent,false)
        return inventoryViewHolder(view)

    }

    override fun getItemCount(): Int {
        return listPeluches?.size!!

    }




    override fun onBindViewHolder(holder: inventoryViewHolder, position: Int) {
        val peluches = listPeluches!![position]
        holder.loadItem(peluches)
    }




    class inventoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun loadItem(peluche:Peluches){

            itemView.tNombre.text = peluche.nombre
            itemView.tid.text = peluche.id
            itemView.tPrecio.text = peluche.precio
            itemView.tStock.text = peluche.cantidad

            itemView.setOnClickListener{
                Toast.makeText(itemView.context,peluche.nombre,Toast.LENGTH_LONG).show()
            }

        }
    }
}