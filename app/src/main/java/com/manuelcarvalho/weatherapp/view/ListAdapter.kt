package com.manuelcarvalho.weatherapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.manuelcarvalho.weatherapp.R



private const val TAG = "ListAdapter"

class ListAdapter(
    //val cartList: ArrayList<Part>,
    private val callback: OnClickListenerInterface

) :
    RecyclerView.Adapter<ListAdapter.CartViewHolder>() {

//    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(
            R.layout.list_view
            , parent, false
        )
        return CartViewHolder(view)
    }

    override fun getItemCount() = cartList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.view.tv_cart.text = cartList[position].catridge


        holder.view.checkBox.setOnClickListener {
            val num = cartList[position].uuid
            Log.d(TAG, "${cartList[position].uuid} clicked")
            callback.onClick(num)
        }


        holder.view.setOnClickListener {
            val cart = cartList[position].catridge
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