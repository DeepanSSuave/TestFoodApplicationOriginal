package com.example.testfoodapplication.testfoodapplication.view.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testfoodapplication.R
import com.example.testfoodapplication.testfoodapplication.model.Checkouts
import com.example.testfoodapplication.testfoodapplication.model.Food
import com.example.testfoodapplication.testfoodapplication.model.db.AppDatabase
import com.example.testfoodapplication.testfoodapplication.view.cart.CartItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_transaction.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class TransactionFragment : Fragment() {

    @Inject
    lateinit var db : AppDatabase

    lateinit var transactionAdapter : TransactionAdapter

    var listOfCheckouts = mutableListOf<Checkouts>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        transactionAdapter = TransactionAdapter()
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transaction_recycler_view.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = transactionAdapter
        }

        lifecycleScope.launch {
            listOfCheckouts.addAll(db.transactionDao().getAll() as MutableList<Checkouts>)
            transactionAdapter.setData(listOfCheckouts)
        }

    }
}