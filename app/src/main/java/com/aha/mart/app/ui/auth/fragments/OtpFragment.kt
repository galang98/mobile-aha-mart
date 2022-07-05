package com.aha.mart.app.ui.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.aha.mart.app.R
import com.aha.mart.app.data.network.ApiEndPoint
import com.aha.mart.app.data.network.Resource
import com.aha.mart.app.data.repositories.AuthRepository
import com.aha.mart.app.databinding.FragmentOtpBinding
import com.aha.mart.app.ui.auth.AuthActivity
import com.aha.mart.app.ui.auth.AuthViewModel
import com.aha.mart.app.ui.base.BaseFragment
import com.aha.mart.app.utill.helperSaveUserAndToken

class OtpFragment : BaseFragment<AuthViewModel, FragmentOtpBinding, AuthRepository>() {

    private lateinit var activity : AuthActivity
    private lateinit var phone: String
    private val args: OtpFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setArg()
        setUp()
        setInput()
        setObserve()
    }

    private fun setArg() {
        phone = args.phone
    }

    private fun setUp() {
        activity = requireActivity() as AuthActivity
        activity.getActivityBinding().toolbarAuth.toolbar.visibility = View.VISIBLE
        getString(R.string.lbl_phone_number,phone).also { binding.phoneEdit.text = it }
    }

    private fun setInput() {
        binding.pinView.addTextChangedListener {
            if(it.toString().length == 6) {
                viewModel.verifyOtp(phone,it.toString())
                progressbar(true)
            }
        }
    }

    private fun setObserve() {
        viewModel.verifyResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->  {
                    progressbar(false)
                    activity.openMainActivity()
                    helperSaveUserAndToken(requireContext(),it.value)
                }
                is Resource.Failure ->  {
                    progressbar(false)
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun progressbar(visible: Boolean){
        binding.progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun getViewModel() = AuthViewModel::class.java
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentOtpBinding.inflate(inflater,container,false)
    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(requireContext(),ApiEndPoint::class.java))
}