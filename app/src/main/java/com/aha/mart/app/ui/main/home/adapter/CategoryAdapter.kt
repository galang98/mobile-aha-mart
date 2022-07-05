package com.aha.mart.app.ui.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aha.mart.app.data.network.model.response.CategoryResponse
import com.aha.mart.app.databinding.ListCategoryBinding
import com.aha.mart.app.ui.base.BaseViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class CategoryAdapter(
    val context: Context,
    var list: List<CategoryResponse.Category>
) : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val binding = ListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return CategoryVH(binding)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return 6
    }

    inner class CategoryVH(
        private val binding: ListCategoryBinding
    ): BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
//            val data : CategoryResponse.Category = list.get(position)
//
//            Glide.with(context)
//                .load(data.image)
//                .into(binding.ivImage)
//
//            binding.lblName.text = data.name

        }

        override fun clear() {

        }
    }
}