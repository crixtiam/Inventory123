package com.example.inventory123

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_add_item.*
import kotlinx.android.synthetic.main.fragment_add_item.view.*
import kotlinx.android.synthetic.main.fragment_delete_item.view.*

class deleteItemFragment: Fragment() {

    var interfaz : comunicator ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_delete_item, container, false)

        root.btn_delete.setOnClickListener{
            var ids = root.eDeleteSearch.text.toString()


            if(ids.isEmpty() ){
                Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
            }
            else{

                val builder = AlertDialog.Builder(this.context)
                builder.setTitle("ARE YOU SURE DELETE THIS ITEM?")
                builder.setMessage("ID: $ids")
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                    interfaz?.deleteItem(ids)
                    root.eDeleteSearch.text.clear()
                    Toast.makeText(context,"Item Delete", Toast.LENGTH_SHORT).show()


                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(context,"No Delete", Toast.LENGTH_SHORT).show()
                }
                builder.show()
                }

        }
        return root

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            interfaz = context as comunicator
        }catch (e: ClassCastException){
            Log.d("exception", e.toString())
        }
    }

}