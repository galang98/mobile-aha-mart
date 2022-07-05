package com.aha.mart.app.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aha.mart.app.data.network.ApiEndPoint
import com.aha.mart.app.data.network.model.response.UserResponse
import com.aha.mart.app.data.repositories.MainRepository
import com.aha.mart.app.databinding.FragmentFavoriteBinding
import com.aha.mart.app.ui.base.BaseFragment
import com.aha.mart.app.utill.helperGetUserAndToken

class FavoriteFragment : BaseFragment<FavoriteViewModel, FragmentFavoriteBinding, MainRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        var user : UserResponse = helperGetUserAndToken(requireContext())
        binding.lblGreat.text = "Wilujeng sumping "+user.customer?.name
    }

    override fun getViewModel() = FavoriteViewModel::class.java
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentFavoriteBinding.inflate(inflater,container,false)
    override fun getFragmentRepository() = MainRepository(remoteDataSource.buildApi(requireContext(),ApiEndPoint::class.java))
}