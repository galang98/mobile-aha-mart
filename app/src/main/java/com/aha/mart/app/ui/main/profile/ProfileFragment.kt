package com.aha.mart.app.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aha.mart.app.data.network.ApiEndPoint
import com.aha.mart.app.data.repositories.MainRepository
import com.aha.mart.app.databinding.FragmentProfileBinding
import com.aha.mart.app.ui.auth.AuthActivity
import com.aha.mart.app.ui.base.BaseFragment
import com.aha.mart.app.ui.main.MainActivity
import com.aha.mart.app.utill.helperLogout

class ProfileFragment :  BaseFragment<ProfileViewHolder, FragmentProfileBinding, MainRepository>() {

    private lateinit var activity : MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        activity = requireActivity() as MainActivity
        binding.btnLogout.setOnClickListener {
            helperLogout(requireContext())
            activity.openAuthActivity()
        }
    }

    override fun getViewModel() = ProfileViewHolder::class.java
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentProfileBinding.inflate(inflater,container,false)
    override fun getFragmentRepository() = MainRepository(remoteDataSource.buildApi(requireContext(),ApiEndPoint::class.java))
}