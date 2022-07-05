package com.aha.mart.app.ui.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aha.mart.app.data.network.model.Carousel
import com.aha.mart.app.databinding.ListCarouselBinding
import com.aha.mart.app.ui.base.BaseViewHolder
import io.github.vejei.carouselview.CarouselAdapter

class PageAdapter(
    val context: Context,
    var list: List<Carousel>
) : CarouselAdapter<PageAdapter.ViewHolder>(){

    override fun onCreatePageViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListCarouselBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindPageViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getPageCount(): Int {
        return 4;
    }

    inner class ViewHolder(
        private val binding : ListCarouselBinding
    ) : BaseViewHolder(binding.root){

        override fun onBind(position: Int) {
            super.onBind(position)

        }

        override fun clear() {
        }

    }
}