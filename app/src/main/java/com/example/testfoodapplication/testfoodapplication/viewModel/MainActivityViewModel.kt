package com.example.testfoodapplication.testfoodapplication.viewModel

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testfoodapplication.testfoodapplication.model.Category
import com.example.testfoodapplication.testfoodapplication.model.Food
import com.example.testfoodapplication.testfoodapplication.view.home.AdapterConstants
import com.example.testfoodapplication.testfoodapplication.view.home.CategoryAdapter
import com.example.testfoodapplication.testfoodapplication.view.home.FoodAdapter
import dagger.hilt.android.lifecycle.HiltViewModel

class MainActivityViewModel : ViewModel() {

    private var _foodList = MutableLiveData(listOf<Food>())
    private val _categoryList = MutableLiveData(listOf<Category>())
    val foodList : LiveData<List<Food>> = _foodList
    val categoryList : LiveData<List<Category>> = _categoryList
    private var idOfItem: Int = 0

    val itemsAdapter = FoodAdapter(arrayListOf())

    fun addFoodList() : LiveData<List<Food>> {
         _foodList.value = AdapterConstants.FOODS
        return  _foodList
    }

    fun addCateGoryList() : LiveData<List<Category>> {
        _categoryList.value = AdapterConstants.CATEGORIES
        return _categoryList
    }

}