package com.aha.mart.app.ui.auth.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.aha.mart.app.R
import com.aha.mart.app.data.network.ApiEndPoint
import com.aha.mart.app.data.network.Resource
import com.aha.mart.app.data.repositories.AuthRepository
import com.aha.mart.app.databinding.FragmentLoginBinding
import com.aha.mart.app.ui.auth.AuthActivity
import com.aha.mart.app.ui.auth.AuthViewModel
import com.aha.mart.app.ui.base.BaseFragment
import com.aha.mart.app.utill.helperPhoneValidation
import com.aha.mart.app.utill.toast

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    private var phone: String = ""
    private var isWhatsapp : Boolean = false
    private lateinit var viewFragment : View
    private lateinit var activity : AuthActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewFragment = view
        setUp()
        setClickable()
        setObserve()
    }

    private fun setUp() {
        activity = requireActivity() as AuthActivity
        activity.getActivityBinding().toolbarAuth.toolbar.visibility = View.VISIBLE

        binding.optionSms.setOnClickListener{
            setOptionSms(true)
            setOptionWhatsapp(false)
        }
        binding.optionWhatsapp.setOnClickListener{
            setOptionSms(false)
            setOptionWhatsapp(true)
        }
        binding.optionSmsRb.setOnClickListener{
            setOptionSms(true)
            setOptionWhatsapp(false)
        }
        binding.optionWhatsappRb.setOnClickListener{
            setOptionSms(false)
            setOptionWhatsapp(true)
        }
    }

    private fun setOptionSms(checked : Boolean = false){
        if(checked) {
            binding.optionSmsRb.isChecked = true
            binding.optionSms.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.background_outline_primary)
            binding.optionSmsRb.buttonTintList = generateColorStateList(
                enabledColor = ContextCompat.getColor(requireContext(), R.color.alizarim)
            )
        }else{
            binding.optionSmsRb.isChecked = false
            binding.optionSms.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.background_outline_grey)
            binding.optionSmsRb.buttonTintList = generateColorStateList(
                enabledColor = ContextCompat.getColor(requireContext(), R.color.divider)
            )
        }
    }

    private fun setOptionWhatsapp(checked : Boolean = false){
        if(checked) {
            binding.optionWhatsappRb.isChecked = true
            binding.optionWhatsapp.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.background_outline_primary)
            binding.optionWhatsappRb.buttonTintList = generateColorStateList(
                enabledColor = ContextCompat.getColor(requireContext(), R.color.alizarim)
            )
        }else{
            binding.optionWhatsappRb.isChecked = false
            binding.optionWhatsapp.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.background_outline_grey)
            binding.optionWhatsappRb.buttonTintList = generateColorStateList(
                enabledColor = ContextCompat.getColor(requireContext(), R.color.divider)
            )
        }
    }

    private fun generateColorStateList(
        enabledColor:Int = ContextCompat.getColor(requireContext(),R.color.divider)
    ): ColorStateList {
        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled)
        )
        val colors = intArrayOf(
            enabledColor
        )
        return ColorStateList(states, colors)
    }

    private fun setClickable() {
        binding.buttonSendOtp.setOnClickListener{
            if(!valid()) return@setOnClickListener
            viewModel.login(phone,isWhatsapp)
            progressbar(true)
        }
        binding.btnRegister.setOnClickListener {
            activity.openRegister(viewFragment)
        }
    }

    private fun setObserve() {
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->  {
                    progressbar(false)
                    activity.openOTP(viewFragment,phone)
                }
                is Resource.Failure ->  {
                    progressbar(false)
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun valid(): Boolean {
        phone = binding.etPhone.text.toString().trim()
        val smsCb = binding.optionSmsRb.isChecked
        val whatsappCb = binding.optionWhatsappRb.isChecked

        if(phone.isNullOrEmpty()){
            requireContext().toast("Nomor Ponsel Tidak Boleh Kosong")
            return false
        }
        if(!helperPhoneValidation(phone)){
            requireContext().toast("Format nomor ponsel salah")
            return false
        }

        if(whatsappCb || smsCb){
            if(whatsappCb) isWhatsapp = true
            if(smsCb) isWhatsapp = false
        }else{
            requireContext().toast("Metode OTP harus dipilih")
            return false
        }

        return true
    }

    private fun progressbar(visible: Boolean){
        binding.progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun getViewModel() = AuthViewModel::class.java
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentLoginBinding.inflate(inflater,container,false)
    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(requireContext(),ApiEndPoint::class.java))


}