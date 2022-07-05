package com.aha.mart.app.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.aha.mart.app.R
import com.aha.mart.app.data.network.ApiEndPoint
import com.aha.mart.app.data.network.model.response.ProductResponse
import com.aha.mart.app.data.repositories.MainRepository
import com.aha.mart.app.databinding.FragmentHomeBinding
import com.aha.mart.app.ui.base.BaseFragment
import com.aha.mart.app.ui.main.home.adapter.CategoryAdapter
import com.aha.mart.app.ui.main.home.adapter.BaseProductAdapter
import com.aha.mart.app.ui.main.home.adapter.PageAdapter
import com.aha.mart.app.utill.sealed.ListProductType
import io.github.vejei.viewpagerindicator.indicator.CircleIndicator

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, MainRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategory()
        initBanner()
        initFlashsale()
        initPopular()
        initTerbaru()
    }

    private fun initCategory() {
        val adapterCategory = CategoryAdapter(requireContext(), ArrayList())
        binding.rvCategory.apply {
            adapter = adapterCategory
            isNestedScrollingEnabled = false
            layoutManager = GridLayoutManager(requireContext(), 6, GridLayoutManager.VERTICAL, false)
        }
    }

    private fun initBanner() {
        val adapterCarousel = PageAdapter(requireContext(), ArrayList())
        binding.rvCarousel.apply {
            adapter = adapterCarousel
        }
        binding.rvIndicator.apply {
            setWithViewPager2(binding.rvCarousel.viewPager2,false)
            setAnimationMode(CircleIndicator.AnimationMode.SLIDE)
            itemCount = 4
        }
    }

    private fun initFlashsale() {
        val itemDecoration = DividerItemDecoration(requireContext(), GridLayoutManager.HORIZONTAL)
        itemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider_category)!!)

        val adapterFlashsale = BaseProductAdapter(requireContext(),ListProductType.Flashsale)
        binding.rvFlashsale.apply {
            adapter = adapterFlashsale
            layoutManager = GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
            addItemDecoration(itemDecoration)
        }

        var listPopular : MutableList<ProductResponse> = mutableListOf()
        listPopular.add(ProductResponse(R.drawable.ex_lays,"Lays"))
        listPopular.add(ProductResponse(R.drawable.ex_cola,"Coca-Cola"))
        listPopular.add(ProductResponse(R.drawable.ex_lays,"Lays"))
        listPopular.add(ProductResponse(R.drawable.ex_cola,"Coca-Cola"))
        adapterFlashsale.updateData(listPopular)
    }

    private fun initPopular() {
        val adapterPopular = BaseProductAdapter(requireContext(),ListProductType.Common)
        binding.rvPopular.apply {
            adapter = adapterPopular
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }

        var listPopular : MutableList<ProductResponse> = mutableListOf()
        listPopular.add(ProductResponse(R.drawable.ex_lays,"Lays"))
        listPopular.add(ProductResponse(R.drawable.ex_cola,"Coca-Cola"))
        listPopular.add(ProductResponse(R.drawable.ex_cola,"Coca-Cola"))
        listPopular.add(ProductResponse(R.drawable.ex_lays,"Lays"))
        adapterPopular.updateData(listPopular)
    }

    private fun initTerbaru() {
        val adapterNewest = BaseProductAdapter(requireContext(),ListProductType.Newest)
        binding.rvTerbaru.apply {
            adapter = adapterNewest
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }

        var listNewest : MutableList<ProductResponse> = mutableListOf()
        listNewest.add(ProductResponse(R.drawable.ex_lays,"Lays"))
        listNewest.add(ProductResponse(R.drawable.ex_cola,"Coca-Cola"))
        listNewest.add(ProductResponse(R.drawable.ex_cola,"Coca-Cola"))
        listNewest.add(ProductResponse(R.drawable.ex_lays,"Lays"))
        adapterNewest.updateData(listNewest)
    }

    override fun getViewModel() = HomeViewModel::class.java
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentHomeBinding.inflate(inflater,container,false)
    override fun getFragmentRepository() = MainRepository(remoteDataSource.buildApi(requireContext(),ApiEndPoint::class.java))
}