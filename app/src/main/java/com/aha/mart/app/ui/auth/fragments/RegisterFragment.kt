package com.aha.mart.app.ui.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aha.mart.app.data.network.ApiEndPoint
import com.aha.mart.app.data.repositories.AuthRepository
import com.aha.mart.app.databinding.FragmentRegisterBinding
import com.aha.mart.app.ui.auth.AuthActivity
import com.aha.mart.app.ui.auth.AuthViewModel
import com.aha.mart.app.ui.base.BaseFragment

class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {

    private lateinit var viewFragment : View
    private lateinit var activity : AuthActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewFragment = view
        setUp()
        setClickable()
    }

    private fun setUp() {
        activity = requireActivity() as AuthActivity
        activity.getActivityBinding().toolbarAuth.toolbar.visibility = View.VISIBLE
    }

    private fun setClickable() {
        binding.btnLogin.setOnClickListener{
            activity.onBackPressed()
        }
    }

    override fun getViewModel() = AuthViewModel::class.java
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentRegisterBinding.inflate(inflater,container,false)
    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(requireContext(),ApiEndPoint::class.java))

}