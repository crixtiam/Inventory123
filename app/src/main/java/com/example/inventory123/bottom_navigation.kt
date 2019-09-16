package com.example.inventory123


import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.example.inventory123.addItemFragment
import com.example.inventory123.inventoryItemFragment

class bottom_navigation : AppCompatActivity(), comunicator {



    var peluchito : MutableList<Peluches> = ArrayList()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        when (item.itemId) {
            R.id.navigation_add -> {
                val addFragment = addItemFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("pelu",ArrayList<Peluches>(peluchito))
                addFragment.arguments=bundle
                transaction.replace(R.id.contenedor,addFragment).commit()

                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_search -> {
                val searchItem = searchItemFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("pelu", ArrayList<Peluches>(peluchito))
                searchItem.arguments = bundle
                transaction.replace(R.id.contenedor,searchItem).commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_delete -> {
               val deleteFragment = deleteItemFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("pelu", ArrayList<Peluches>(peluchito))
                deleteFragment.arguments = bundle
                transaction.replace(R.id.contenedor,deleteFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_inventory -> {
                val stockFragment = inventoryItemFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("pelu", ArrayList<Peluches>(peluchito))
                stockFragment.arguments = bundle
                transaction.replace(R.id.contenedor,stockFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val addFragment = addItemFragment()

        transaction.add(R.id.contenedor,addFragment).commit()

        peluchito.add(Peluches("001","peluche 1","$1000","100"))
        peluchito.add(Peluches("002","peluche 2","$2000","1"))


    }


    override fun deleteItem(id: String) {



        peluchito.removeAll{it.id?.toLowerCase()==id.toLowerCase()}

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val eliminarFragment = deleteItemFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("pelu", ArrayList<Peluches>(peluchito))

        eliminarFragment.arguments = bundle
        transaction.replace(R.id.contenedor,eliminarFragment).commit()
    }

    override fun addItem(id: String, nombre: String, precio: String, cantidad: String) {

        var peluche = Peluches(id,nombre,precio,cantidad)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragmentAddNotify = addItemFragment()
        val bundle = Bundle()
        var findEqual = false

        for (i in peluchito){

            if (i.id!!.toLowerCase()== id.toLowerCase()){
                findEqual = true
                break
            }

        }

        if (findEqual)
        {
            bundle.putString("pelu","ID Equal, please input diferent ID")
        }
        else
        {
            peluchito.add(peluche)
        }
        fragmentAddNotify.arguments = bundle
        transaction.replace(R.id.contenedor,fragmentAddNotify).commit()

    }

    override fun searchItem(id: String) {

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragmentSendValue = searchItemFragment()
        val bundle = Bundle()

        var findIdItem:String=""

         for (i  in peluchito)
        {
            if (i.id!!.toLowerCase()== id.toLowerCase()){
                findIdItem="Encontro el Id search $id"
                bundle.putString("pelu","OK")
                break
            }

        }

        Log.d("pelu",findIdItem)
        fragmentSendValue.arguments = bundle
        transaction.replace(R.id.contenedor,fragmentSendValue).commit()

    }
}