package com.example.testfoodapplication.testfoodapplication

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testfoodapplication.R
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.appbar_layout.*
import kotlinx.android.synthetic.main.navigation_menu_drawer.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var drawer: DrawerLayout? = null
    var navigation: NavigationView? = null
    private var navview: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_menu_drawer)

        hamburger_icon.setOnClickListener() {
            openDrawer()
        }

        val navView: NavigationView = findViewById(R.id.navigationView)
        navview = navView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.Menu -> {
                    navController.navigate(R.id.main_fragment, null)
                }

                R.id.Settings -> {
                    navController.navigate(R.id.setting_fragment, null)
                }

                R.id.Transaction -> {
                    navController.navigate(R.id.transaction_fragment, null)
                }
                R.id.cart -> {
                    navController.navigate(R.id.cart_fragment,null)
                }
                R.id.logout -> {
                    val alertDialogBuilder = AlertDialog.Builder(this)
                    alertDialogBuilder.setTitle("Do you want to Logout ?")
                    alertDialogBuilder.setMessage("App will close when you press YES")
                    alertDialogBuilder.setPositiveButton("YES") { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    }
                    alertDialogBuilder.setNegativeButton("NO") { dialog, _ ->
                        dialog.dismiss()
                    }
                    val alertDialog = alertDialogBuilder.create()
                    alertDialog.show()
                }
            }
            closeDrawer()
            true
        }

        drawer = drawer_layout
        navigation = navigationView

        drawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

//        navigation?.menu?.findItem(R.id.Menu)?.setOnMenuItemClickListener {
//            closeDrawer()
//            false
//        }
//
//        navigation?.menu?.findItem(R.id.Settings)?.setOnMenuItemClickListener {
//            closeDrawer()
//            false
//        }
    }

    fun openDrawer() {
        drawer?.open()
    }

    fun closeDrawer() {
        drawer?.close()
    }


}