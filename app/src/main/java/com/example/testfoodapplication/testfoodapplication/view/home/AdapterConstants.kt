package com.example.testfoodapplication.testfoodapplication.view.home

import com.example.testfoodapplication.R
import com.example.testfoodapplication.testfoodapplication.model.Category
import com.example.testfoodapplication.testfoodapplication.model.Food

object AdapterConstants {
    val CATEGORIES = listOf(
        Category(0,"All", R.drawable.food_splash),
        Category(1,"pizza", R.drawable.hawaiian_pizza),
        Category(2,"burger", R.drawable.delicious_cheeseburger),
        Category(3,"fresh_juice", R.drawable.juice)
    )

    val FOODS = listOf(
        Food(1,"Hawaiian Pizza",10,1,0, R.drawable.pizza_img),
        Food(2,"Cheese Pizza", 10,1,0, R.drawable.pizza_img),
        Food(3,"Veggie Pizza",10,1,0, R.drawable.pizza_img),
        Food(4,"Pepperoni Pizza",10,1,0, R.drawable.pizza_img),
        Food(5,"Meat Pizza",10,1,0, R.drawable.pizza_img),
        Food(6,"Margherita Pizza",10,1,0, R.drawable.pizza_img),
        Food(8,"BBQ Chicken Pizza",10,1,0, R.drawable.pizza_img),
        Food(9,"Buffalo Pizza",10,1,0, R.drawable.pizza_img),

        Food(10,"Turkey burger",10,2,0, R.drawable.cheese_burger),
        Food(11,"mushroom burger",10,2,0, R.drawable.cheese_burger),
        Food(12,"Veggie burger",10,2,0, R.drawable.cheese_burger),
        Food(13,"Wild salmon burger",10,2,0, R.drawable.cheese_burger),
        Food(14,"Bean burger",10,2,0, R.drawable.cheese_burger),
        Food(15,"Cheese burger",10,2,0, R.drawable.cheese_burger),
        Food(16,"Cheese burger 12",10,2,0, R.drawable.cheese_burger),

        Food(17,"Sugarcane juice",10,3,0, R.drawable.juice),
        Food(18,"Grapefruit juice",10,3,0, R.drawable.juice),
        Food(19,"mango plum juice",10,3,0, R.drawable.juice),
        Food(20,"orange juice",10,3,0, R.drawable.juice)
    )
}