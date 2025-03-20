package com.clikzop.mvvm_complete.ui.auth.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clikzop.mvvm_complete.data.network.NetworkState
import com.clikzop.mvvm_complete.data.repository.Mainrepository
import com.clikzop.mvvm_complete.ui.auth.model.LoginRequest
import com.clikzop.mvvm_complete.ui.auth.model.LoginResponse
import kotlinx.coroutines.launch

class ViewModelLogin(private val mainrepository: Mainrepository) : ViewModel() {

    private val loginresData = MutableLiveData<NetworkState<LoginResponse>>()
    val _loginData: LiveData<NetworkState<LoginResponse>> get() = loginresData

    fun loginCall(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginresData.value = mainrepository.loginApi(loginRequest)
        }
    }
}