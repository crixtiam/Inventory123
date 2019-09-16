package com.example.inventory123

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_search_item.*
import kotlinx.android.synthetic.main.fragment_search_item.view.*
import java.lang.ClassCastException
import java.util.Locale.filter

class searchItemFragment : Fragment() {

    var interfaz : comunicator? = null
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_search_item,container,false)
        var keyPrint = ""
        var txtSetText =  view.textViewItem



/*

        var peluchito = arguments?.getParcelableArrayList<Peluches>("pelu")
        peluchito?.toMutableList()
        recyclerView  = view.findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context,RecyclerView.VERTICAL,false)

        val searchPeluchesAdapter = searchItemAdapter(peluchito!!,this.requireContext())
        recyclerView.adapter = searchPeluchesAdapter
*/






        var keySend = arguments?.getString("pelu")

        view.btn_search.setOnClickListener{
           // var nombreb = view.eSearchItem.text.toString()
            var eid2 = view.eSearchItem.text.toString()

            if (eid2.isEmpty()){
                Toast.makeText(context,"empty",Toast.LENGTH_LONG).show()

            }
            else
            {   interfaz?.searchItem(eid2)

           ///     searchPeluchesAdapter.filter.filter(nombreb)

                Log.d("pelu","esta en el else search")
            }


        }


        if (keySend==null || keySend.trim()==""){
            keyPrint="Item No encontrado"
        }
        else
        {
            keyPrint="Item encontrado"
        }

        txtSetText.setText(keyPrint).toString()

        return view
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