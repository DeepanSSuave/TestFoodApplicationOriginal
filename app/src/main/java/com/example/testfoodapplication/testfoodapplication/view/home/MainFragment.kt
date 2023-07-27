package com.example.testfoodapplication.testfoodapplication.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testfoodapplication.R
import com.example.testfoodapplication.databinding.MainFragmentBinding
import com.example.testfoodapplication.testfoodapplication.model.Food
import com.example.testfoodapplication.testfoodapplication.model.db.AppDatabase
import com.example.testfoodapplication.testfoodapplication.viewModel.MainActivityViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(), OnItemClickListener {

    @Inject
    lateinit var database: AppDatabase
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var foodAdapter: FoodAdapter
    lateinit var binding: MainFragmentBinding
    private var idOfItem: Int = 0
    var foodItem = listOf<Food>()
//    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        foodItem = AdapterConstants.FOODS
        foodAdapter = FoodAdapter(arrayListOf())
        foodAdapter.setFoodData(foodItem)

//        viewModel.foodList.observe(viewLifecycleOwner) {
//            it?.let { foods ->
//                foodAdapter.setFoodData(foods)
//            }
//        }
        categoryAdapter = CategoryAdapter { id ->
            Toast.makeText(requireContext(), "category id = $id", Toast.LENGTH_SHORT).show()
            foodItem = if (id == 0) {
                idOfItem = 0
                AdapterConstants.FOODS
            } else {
                idOfItem = id
                AdapterConstants.FOODS.filter { it.category == id }
            }
            foodAdapter.setFoodData(foodItem)
        }


        foodAdapter.setOnItemClickListener(this)

        binding.apply {
            categoryRV.adapter = categoryAdapter
            foodItemRV.adapter = foodAdapter
        }

        categoryAdapter.setCategoryData(AdapterConstants.CATEGORIES)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onItemClick(position: Int) {
        val json = Gson().toJson(foodItem[position])
        findNavController().navigate(
            R.id.actionFromMainFragmentToAddItemFragment,
            bundleOf("food" to json)
        )
    }
}