package com.aha.mart.app.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aha.mart.app.data.repositories.AuthRepository
import com.aha.mart.app.data.repositories.BaseRepository
import com.aha.mart.app.data.repositories.MainRepository
import com.aha.mart.app.ui.auth.AuthViewModel
import com.aha.mart.app.ui.main.MainViewModel
import com.aha.mart.app.ui.main.favorite.FavoriteViewModel
import com.aha.mart.app.ui.main.home.HomeViewModel
import com.aha.mart.app.ui.main.order.OrderViewModel
import com.aha.mart.app.ui.main.profile.ProfileViewHolder
import java.lang.IllegalArgumentException

class ViewModelFactory (
    private val repository : BaseRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(repository as MainRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as MainRepository) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(repository as MainRepository) as T
            modelClass.isAssignableFrom(OrderViewModel::class.java) -> OrderViewModel(repository as MainRepository) as T
            modelClass.isAssignableFrom(ProfileViewHolder::class.java) -> ProfileViewHolder(repository as MainRepository) as T
            else -> throw IllegalArgumentException("View Model Classnot found")
        }
    }

}