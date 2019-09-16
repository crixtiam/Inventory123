package com.example.inventory123

import com.example.inventory123.R
import com.example.inventory123.comunicator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_add_item.view.*
import java.lang.ClassCastException

class addItemFragment: Fragment()
{
    var interfaz : comunicator ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_add_item, container, false)
        var keySend = arguments?.getString("pelu").toString()


        root.btn_add.setOnClickListener{
            var ids = root.eId.text.toString()
            var nom = root.eName.text.toString()
            var pre = root.ePrice.text.toString()
            var cant = root.eStock.text.toString()

            if(ids.isEmpty() || nom.isEmpty() || pre.isEmpty() || cant.isEmpty()){
                Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
            }else{

                val builder = AlertDialog.Builder(this.context)
                builder.setTitle("CONFIRMAR INGRESO")
                builder.setMessage("ID: $ids \nNombre: $nom \nPrecio: $pre \nCantidad $cant \n")

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    interfaz?.addItem(ids,nom,pre,cant)
                    root.eId.text.clear()
                    root.eName.text.clear()
                    root.ePrice.text.clear()
                    root.eStock.text.clear()


                    when(keySend){
                        "KO"-> Toast.makeText(context,"ID Equal please change ID", Toast.LENGTH_LONG).show()
                        "OK"->Toast.makeText(context,"Item Added", Toast.LENGTH_LONG).show()
                        null-> Toast.makeText(context,"Item Null", Toast.LENGTH_LONG).show()

                    }

                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(context,"No Added", Toast.LENGTH_SHORT).show()
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


