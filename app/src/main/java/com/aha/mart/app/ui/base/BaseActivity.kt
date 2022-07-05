package com.aha.mart.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.aha.mart.app.data.network.RemoteDataSource
import com.aha.mart.app.data.repositories.BaseRepository

abstract class BaseActivity<VM : ViewModel, B : ViewBinding, R: BaseRepository> : AppCompatActivity() {
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getActivityBinding()
        setContentView(binding.root)

        val factory = ViewModelFactory(getActivityRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        setUp()
    }

    fun setToolbar(backButton : Boolean){
        if (backButton) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setHomeAsUpIndicator(com.aha.mart.app.R.drawable.ic_back)
        }
    }

    abstract fun getViewModel() : Class<VM>
    abstract fun getActivityBinding() : B
    abstract fun getActivityRepository() : R
    abstract fun setUp()
}