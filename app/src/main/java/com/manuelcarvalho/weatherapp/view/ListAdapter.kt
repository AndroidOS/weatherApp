package com.manuelcarvalho.weatherapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.manuelcarvalho.weatherapp.R



private const val TAG = "ListAdapter"

class ListAdapter(
    //val weatherList: ArrayList<Part>,
    private val callback: OnClickListenerInterface

) :
    RecyclerView.Adapter<ListAdapter.CartViewHolder>() {

    private val weatherList = arrayOf(1,2,3,4)
    private lateinit var tv_weather: TextView
//

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(
            R.layout.list_view
            , parent, false
        )
        tv_weather = view.findViewById(R.id.tv_weather)
        return CartViewHolder(view)
    }

    override fun getItemCount() = weatherList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.view.tv_weather = weatherList[position]


//        holder.view.checkBox.setOnClickListener {
//            val num = weatherList[position].uuid
//            Log.d(TAG, "${weatherList[position].uuid} clicked")
  //          callback.onClick(num)
 //       }


        holder.view.setOnClickListener {
           // val cart = weatherList[position].catridge
//            Navigation.findNavController(it)
//                .navigate(ListFragmentDirections.actionFirstFragmentToDetailFragment(cart))


        }


    }

    //.navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment())
    class CartViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}

interface OnClickListenerInterface {
    fun onClick(num: Int)
    fun onChoice(string: String)
}