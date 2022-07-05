package com.aha.mart.app.ui.main.home.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.aha.mart.app.R
import com.aha.mart.app.data.network.model.response.ProductResponse
import com.aha.mart.app.databinding.ListProductCommonBinding
import com.aha.mart.app.databinding.ListProductFlashsaleBinding
import com.aha.mart.app.ui.base.BaseViewHolder
import com.aha.mart.app.utill.getDominantColor
import com.aha.mart.app.utill.sealed.ListProductType
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class BaseProductAdapter(
    val context : Context,
    private var listType : ListProductType,
    private var listProduct : List<ProductResponse> = ArrayList()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateData(listType : List<ProductResponse>){
        this.listProduct = listType
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ViewBinding
        return when(viewType){
            TYPE_FLASHSALE -> {
                binding = ListProductFlashsaleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolderFlashsale(binding)
            }
            TYPE_COMMON -> {
                binding = ListProductCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolderCommon(binding)
            }
            TYPE_NEWEST -> {
                binding = ListProductCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolderNewest(binding)
            }
            else -> throw IllegalArgumentException("Invalid type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TYPE_FLASHSALE -> (holder as ViewHolderFlashsale).onBind(position)
            TYPE_COMMON -> (holder as ViewHolderCommon).onBind(position)
            TYPE_NEWEST -> (holder as ViewHolderNewest).onBind(position)
        }
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(listType) {
            is ListProductType.Flashsale -> TYPE_FLASHSALE
            is ListProductType.Common -> TYPE_COMMON
            is ListProductType.Newest -> TYPE_NEWEST
        }
    }

    companion object {
        private const val TYPE_FLASHSALE = 1
        private const val TYPE_COMMON = 2
        private const val TYPE_NEWEST = 3
    }

    inner class ViewHolderFlashsale(
        val binding : ListProductFlashsaleBinding
    ) : BaseViewHolder(binding.root){

        override fun onBind(position: Int) {
            super.onBind(position)

            val data = listProduct[position]

            Glide.with(context).load(data.image).into(binding.ivImage)
        }

        override fun clear() {

        }
    }

    inner class ViewHolderCommon(
        val binding : ListProductCommonBinding
    ) : BaseViewHolder(binding.root){

        override fun onBind(position: Int) {
            super.onBind(position)

            val data = listProduct[position]

            binding.lblName.text = data.name
            Glide.with(context).load(data.image).into(binding.ivImage)
            Glide.with(context).asBitmap().load(data.image).into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    binding.ivBg.setBackgroundColor(getDominantColor(resource))
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })
        }

        override fun clear() {

        }
    }

    inner class ViewHolderNewest(
        val binding : ListProductCommonBinding
    ) : BaseViewHolder(binding.root){
        override fun onBind(position: Int) {
            super.onBind(position)

            val data = listProduct[position]

            binding.lblName.text = data.name
            Glide.with(context).load(data.image).into(binding.ivImage)
            binding.ivBg.setBackgroundColor(ContextCompat.getColor(context,R.color.grey_card))
        }

        override fun clear() {

        }

    }
}