package com.jinglebroda.presentation.navigator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.NavigationUI
import com.jinglebroda.presentation.R

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SenderOfCatsAndDucks)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHost = supportFragmentManager.findFragmentById(
            R.id.FragmentLayout
        ) as NavHostFragment
        navController = navHost.navController

        //задание кнопок навигации (стрелки назад) в тулбаре
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean  =
        navController.navigateUp() || super.onSupportNavigateUp()

    override fun showNextScreen(f: Fragment, args: Bundle?) {
        try{
            navController.launchDestination(f.destinationID()!!, args)
        }
        catch(e:Exception){
            Log.d("ErrorNavigation", "destinationID = null")
        }
    }

    override fun back() {
        onBackPressed()
    }
}