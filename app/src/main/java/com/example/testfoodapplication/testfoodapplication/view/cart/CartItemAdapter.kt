package com.example.testfoodapplication.testfoodapplication.view.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testfoodapplication.R
import com.example.testfoodapplication.testfoodapplication.model.Food
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.view.*

class CartItemAdapter : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    private var itemList = emptyList<Food>()

    private var listener: IncrementDecrementListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Food>) {
        if (list.isEmpty()) return
        this.itemList = list
        notifyDataSetChanged()
    }

    fun setListener(listener: IncrementDecrementListener?) {
        this.listener = listener
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.cart_item_image
        val name: TextView = view.cart_item_name
        private val price: TextView = view.cart_item_price
        private val itemCount: TextView = view.txtCount

        @SuppressLint("NotifyDataSetChanged")
        fun setItems(item: Food) {
            image.setImageResource(item.image)
            name.text = item.name
            price.text = (item.price * item.count).toString()
            itemCount.text = item.count.toString()

            view.btnAdd.setOnClickListener {
//                var tempCount = food.count
//                var tempPrice = food.price
                listener?.onIncrement(adapterPosition)
//                itemCount.text = (++tempCount).toString()
//                food.count = tempCount
//                price.text = "$${(tempCount * tempPrice).toString()}"
//                tempPrice *= tempCount
//                food.price = tempPrice
            }
            view.btnMinus.setOnClickListener {
                if (item.count == 0) return@setOnClickListener
//                var tempCount = food.count
//                var tempPrice = food.price
                listener?.onDecrement(adapterPosition)
//                itemCount.text = (--tempCount).toString()
//                food.count = tempCount
//                price.text = "$${(tempCount * tempPrice).toString()}"
//                tempPrice *= tempCount
//                food.price = tempPrice
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItems(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}