package com.clikzop.mvvm_complete.ui.dashboard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clikzop.mvvm_complete.data.repository.Mainrepository

class ViewModelFactory1(private val mainrepository: Mainrepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ViewModelProduct1::class.java)) {
            ViewModelProduct1(this.mainrepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}