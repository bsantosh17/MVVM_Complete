package com.clikzop.mvvm_complete.ui.auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clikzop.mvvm_complete.data.repository.Mainrepository

class ViewModelFactoryLogin(private val mainrepository: Mainrepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ViewModelLogin::class.java)) {
            ViewModelLogin(this.mainrepository) as T
        } else {
            throw IllegalArgumentException("View Model Not Found")
        }
    }
}