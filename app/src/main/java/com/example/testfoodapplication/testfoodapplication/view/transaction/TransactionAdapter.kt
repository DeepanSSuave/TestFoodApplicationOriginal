package com.example.testfoodapplication.testfoodapplication.view.transaction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testfoodapplication.R
import com.example.testfoodapplication.testfoodapplication.model.Checkouts
import com.example.testfoodapplication.testfoodapplication.model.Food
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.transaction_item.view.*

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.InnerViewHolder>() {

    private var transactionList : List<Checkouts> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Checkouts>) {
        if (list.isEmpty()) return
        this.transactionList = list
        notifyDataSetChanged()
    }

    inner class InnerViewHolder(private var view : View) : RecyclerView.ViewHolder(view) {
        private var orderId : Int = 1001
        private val checkoutOrderId : TextView = view.orderId
        private val priceTV: TextView = view.transPriceTextView
        private val timeTV : TextView = view.timeTV
        fun setItems(item: Checkouts) {
            priceTV.text = item.totalPrice.toString()
            timeTV.text = item.time.toString()
            checkoutOrderId.text = orderId.toString()
            orderId++
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent,false)
        return InnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.setItems(transactionList[position])
    }

    override fun getItemCount(): Int = transactionList.size

}