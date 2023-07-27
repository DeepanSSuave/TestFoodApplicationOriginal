package com.example.testfoodapplication.testfoodapplication.view.cart

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testfoodapplication.R
import com.example.testfoodapplication.testfoodapplication.model.Checkouts
import com.example.testfoodapplication.testfoodapplication.model.Food
import com.example.testfoodapplication.testfoodapplication.model.db.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_item_bottomsheet.*
import kotlinx.android.synthetic.main.cart_item.*
import kotlinx.android.synthetic.main.fragment_add_item.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.sql.Time
import java.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment(), IncrementDecrementListener {

    @Inject
    lateinit var db: AppDatabase

    var itemList = mutableListOf<Food>()

    private val totalAmount = 0
    lateinit var cartAdapter: CartItemAdapter
    var totalPrice : Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        cartAdapter = CartItemAdapter()
        cartAdapter.setListener(this)

        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cart_item_recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }

        checkOutBtn.setOnClickListener {
            lifecycleScope.launch {
                Toast.makeText(context,"Checkout Created",Toast.LENGTH_SHORT).show()
                db.transactionDao().insert(Checkouts(0,itemList.size,LocalDateTime.now().toString().substringAfter("T").substringBeforeLast("."),totalPrice))
                db.CartOfItemsDao().deleteTable()
            }
        }


        lifecycleScope.launch {
            itemList.addAll(db.CartOfItemsDao().getAll() as MutableList<Food>)
            cartAdapter.setData(itemList)
            setTotalAmount()
            total_items.text = "${itemList.size} Items"
        }


        nav_image.setOnClickListener {
            this@CartFragment.findNavController().popBackStack()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onIncrement(position: Int) {
        val food = itemList[position]
        food.count += 1
        itemList[position] = food
        cartAdapter.notifyDataSetChanged()
        setTotalAmount()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDecrement(position: Int) {
        val food = itemList[position]
        food.count -= 1
        itemList[position] = food
        cartAdapter.notifyDataSetChanged()
        setTotalAmount()
    }

    private fun setTotalAmount() {
        var tempAmount = 0
        itemList.forEach { food -> tempAmount += (food.count * food.price) }
        total_amount.text = "$ ${tempAmount.toString()}"
        totalPrice = tempAmount
    }
}