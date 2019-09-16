package com.example.inventory123

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory123.Peluches
import com.example.inventory123.R
import kotlinx.android.synthetic.main.fragment_add_item.view.*
import kotlinx.android.synthetic.main.peluche_item.view.*

class inventoryAdapter : RecyclerView.Adapter<inventoryAdapter.inventoryViewHolder>, Filterable {


    private var listPeluches: MutableList<Peluches>? = null
    private var totalListPeluches : MutableList<Peluches>
    private var context : Context? = null

    constructor(listPeluches: MutableList<Peluches>,context: Context){
        this.listPeluches = listPeluches
        totalListPeluches=ArrayList(listPeluches)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): inventoryViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.peluche_item,parent,false)
        return inventoryViewHolder(view)

    }

    override fun getItemCount(): Int {
        return listPeluches?.size!!

    }


    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {


                val charSearch = p0.toString()
                var FilteredList: MutableList<Peluches> = ArrayList()
                if (charSearch.isEmpty()) {

                    FilteredList.addAll(totalListPeluches)

                } else {
                    for (row: Peluches in totalListPeluches) {
                        if (row.nombre!!.toLowerCase().contains(charSearch.toLowerCase())) {
                            FilteredList.add(row)
                        }


                    }
                }

                val filterResult = Filter.FilterResults()
                filterResult.values = FilteredList
                return filterResult
            }



            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                totalListPeluches?.clear()
                totalListPeluches = filterResults!!.values as ArrayList<Peluches>
                notifyDataSetChanged()
            }


        }
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