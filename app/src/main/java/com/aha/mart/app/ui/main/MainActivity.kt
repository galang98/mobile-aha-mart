package com.aha.mart.app.ui.main

import android.content.Intent
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.aha.mart.app.R
import com.aha.mart.app.data.network.ApiEndPoint
import com.aha.mart.app.data.repositories.MainRepository
import com.aha.mart.app.databinding.ActivityMainBinding
import com.aha.mart.app.ui.auth.AuthActivity
import com.aha.mart.app.ui.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jaeger.library.StatusBarUtil

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding, MainRepository>() {

    override fun setUp() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_favorite, R.id.navigation_order
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun openAuthActivity(){
        finish()
        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    override fun getViewModel() = MainViewModel::class.java
    override fun getActivityBinding() = ActivityMainBinding.inflate(layoutInflater)
    override fun getActivityRepository() = MainRepository(remoteDataSource.buildApi(this,ApiEndPoint::class.java))
}