package com.aha.mart.app.ui.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aha.mart.app.data.network.ApiEndPoint
import com.aha.mart.app.data.repositories.AuthRepository
import com.aha.mart.app.databinding.FragmentIntroBinding
import com.aha.mart.app.ui.auth.AuthActivity
import com.aha.mart.app.ui.auth.AuthViewModel
import com.aha.mart.app.ui.base.BaseFragment
import com.aha.mart.app.utill.helperIsUserLoggedIn

class IntroFragment : BaseFragment<AuthViewModel, FragmentIntroBinding, AuthRepository>() {

    private lateinit var activity : AuthActivity
    private lateinit var viewFragment : View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewFragment = view
        setUp()
        setClickable()
    }

    private fun setUp() {
        activity = requireActivity() as AuthActivity
        activity.getActivityBinding().toolbarAuth.toolbar.visibility = View.GONE

        if(helperIsUserLoggedIn(requireContext())){
            activity.finish()
            activity.openMainActivity()
        }
    }

    private fun setClickable() {
        binding.btnNext.setOnClickListener{
            activity.openLogin(viewFragment)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentIntroBinding.inflate(inflater,container,false)
    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(requireContext(),ApiEndPoint::class.java))

}