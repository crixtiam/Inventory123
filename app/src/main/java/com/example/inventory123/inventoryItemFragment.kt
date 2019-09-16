package com.example.inventory123



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory123.R
import com.example.inventory123.inventoryAdapter


class inventoryItemFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_inventory_item,container,false)

        var peluchito = arguments?.getParcelableArrayList<Peluches>("pelu")
        peluchito?.toMutableList()
        recyclerView  = root.findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context,RecyclerView.VERTICAL,false)

        val pelucheAdapter = inventoryAdapter(peluchito!!,this.requireContext())
        recyclerView.adapter = pelucheAdapter


        return root

    }


}
/*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventory123.Peluches
import com.example.inventory123.R
import androidx.recyclerview.widget.RecyclerView

class inventoryItemFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_inventory_item,container,false)

        */
/*var peluchito = arguments?.getParcelableArrayList<Peluches>("peluchesKey")
        peluchito?.toMutableList()
        var peluchitos : MutableList<Peluches> = ArrayList()
            peluchitos = peluchito!!
//
        recyclerView = root.findViewById(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context,RecyclerView.VERTICAL,false)

        val pelucheAdapter = inventoryAdapter(peluchito,this.requireContext())
        recyclerView.adapter = pelucheAdapter*//*


        return root

    }
}*/
