package com.example.testfoodapplication.testfoodapplication.view.addItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testfoodapplication.R
import com.example.testfoodapplication.testfoodapplication.model.Food
import com.example.testfoodapplication.testfoodapplication.model.db.AppDatabase
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_item_bottomsheet.*
import kotlinx.android.synthetic.main.fragment_add_item.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddItemFragment : Fragment() {

    @Inject
    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var tempCount = 0
        var tempPrice = 0

        val stringFoodItem = arguments?.getString("food")
        val foodItem = Gson().fromJson(stringFoodItem, Food::class.java)

        CategoryItemTV.text = foodItem.name.toString()
        CategoryItemPriceTV.text = "$0.00"
        CategoryItemIV.setImageResource(foodItem.image)
        txtCount.text = "0"

        btnAdd.setOnClickListener {
            txtCount.text = (++tempCount).toString()
            tempPrice = tempCount * foodItem.price
            CategoryItemPriceTV.text = "$${tempPrice}"
        }

        btnMinus.setOnClickListener {
            if (tempCount == 0) return@setOnClickListener
            txtCount.text = (--tempCount).toString()
            tempPrice = tempCount * foodItem.price
            CategoryItemPriceTV.text = "$${tempPrice}"
        }

        addToCartBtn.setOnClickListener {
            val food = Food(
                name = foodItem.name,
                category = foodItem.category,
                count = tempCount,
                price = foodItem.price,
                image = foodItem.image
            )

//            val cartItems = CartItems(null, Gson().toJson(food).toString())
            lifecycleScope.launch {
                db.CartOfItemsDao().insert(food)
            }
            Toast.makeText(requireContext(), "Item added to the Cart", Toast.LENGTH_SHORT).show()
            this@AddItemFragment.findNavController().popBackStack()
        }
    }
}