package com.aha.mart.app.ui.main.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aha.mart.app.data.network.ApiEndPoint
import com.aha.mart.app.data.repositories.MainRepository
import com.aha.mart.app.databinding.FragmentOrderBinding
import com.aha.mart.app.ui.base.BaseFragment
import com.aha.mart.app.ui.main.home.HomeViewModel

class OrderFragment : BaseFragment<OrderViewModel, FragmentOrderBinding, MainRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getViewModel() = OrderViewModel::class.java
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentOrderBinding.inflate(inflater,container,false)
    override fun getFragmentRepository() = MainRepository(remoteDataSource.buildApi(requireContext(),ApiEndPoint::class.java))
}