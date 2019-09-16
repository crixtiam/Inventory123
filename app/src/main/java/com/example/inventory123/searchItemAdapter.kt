package com.example.inventory123

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.peluche_item.view.*

class searchItemAdapter:  RecyclerView.Adapter<searchItemAdapter.PeluchitosViewHolder>, Filterable{

    private var listaPeluchitos: MutableList<Peluches>? = null
    private var listaPeluchitosFULL: MutableList<Peluches>
    private var context: Context? = null

    constructor(listPeluchitos: MutableList<Peluches>,context: Context){
        this.listaPeluchitos = listPeluchitos
        listaPeluchitosFULL = ArrayList(listPeluchitos)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeluchitosViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.peluche_item,parent,false)
        return PeluchitosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaPeluchitos?.size!!
    }

    override fun getFilter(): Filter {
        return object : Filter(){

            override fun performFiltering(charString: CharSequence?): FilterResults {

                val charSearch = charString.toString()
                var FilteredList: MutableList<Peluches> = ArrayList()

                if(charSearch.isEmpty()){

                    FilteredList.addAll(listaPeluchitosFULL)

                }else{
                    for(row: Peluches in listaPeluchitosFULL){
                        if(row.nombre!!.toLowerCase().contains(charSearch.toLowerCase())){
                            FilteredList.add(row)
                        }
                    }
                }
                val filterResult = Filter.FilterResults()
                filterResult.values = FilteredList
                return filterResult
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                listaPeluchitos?.clear()
                listaPeluchitos = filterResults!!.values as ArrayList<Peluches>
                notifyDataSetChanged()
            }

        }
    }

    override fun onBindViewHolder(holder: PeluchitosViewHolder, position: Int) {
        var peluchitos = listaPeluchitos!![position]
        holder.loadItem(peluchitos)
    }

    class PeluchitosViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        fun loadItem(peluche:Peluches) {
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