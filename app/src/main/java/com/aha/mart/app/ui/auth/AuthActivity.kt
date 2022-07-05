package com.aha.mart.app.ui.auth

import android.content.Intent
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aha.mart.app.R
import com.aha.mart.app.data.network.ApiEndPoint
import com.aha.mart.app.data.repositories.AuthRepository
import com.aha.mart.app.databinding.ActivityAuthBinding
import com.aha.mart.app.ui.auth.fragments.LoginFragmentDirections
import com.aha.mart.app.ui.base.BaseActivity
import com.aha.mart.app.ui.main.MainActivity

class AuthActivity : BaseActivity<AuthViewModel, ActivityAuthBinding, AuthRepository>() {

    override fun setUp() {
        setSupportActionBar(binding.toolbarAuth.toolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener{_,destination,_ ->

        }
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbarAuth.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    fun openLogin(view : View){
        view.findNavController().navigate(R.id.introToLogin)
    }

    fun openRegister(view : View){
        view.findNavController().navigate(R.id.loginToRegister)
    }

    fun openOTP(view : View, phone : String){
        val action = LoginFragmentDirections.loginToOtp().setPhone(phone)
        view.findNavController().navigate(action)
    }

    fun openMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun getViewModel() = AuthViewModel::class.java
    override fun getActivityBinding() = ActivityAuthBinding.inflate(layoutInflater)
    override fun getActivityRepository() = AuthRepository(remoteDataSource.buildApi(this,ApiEndPoint::class.java))
}