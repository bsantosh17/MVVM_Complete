package com.clikzop.mvvm_complete.ui.dashboard.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clikzop.mvvm_complete.data.network.NetworkState
import com.clikzop.mvvm_complete.data.repository.Mainrepository
import com.clikzop.mvvm_complete.ui.dashboard.product1.ResposeProduct1
import com.clikzop.mvvm_complete.ui.dashboard.product2.ResponseProduct2
import com.clikzop.mvvm_complete.ui.dashboard.product3.ResponseProduct3
import com.clikzop.mvvm_complete.ui.dashboard.product4.ResponseProduct4
import kotlinx.coroutines.launch

class ViewModelProduct1(private val mainrepository: Mainrepository) : ViewModel() {

    private val pro1 = MutableLiveData<ResposeProduct1?>()
    val pro1Live: LiveData<ResposeProduct1?> = pro1

    private val pro2 = MutableLiveData<ResponseProduct2?>()
    val pro2Live:LiveData<ResponseProduct2?>  = pro2

    private val pro3 = MutableLiveData<ResponseProduct3?>()
    val pro3Live:LiveData<ResponseProduct3?> = pro3

    private val pro4 = MutableLiveData<ResponseProduct4?>()
    val pro4Live:LiveData<ResponseProduct4?> = pro4


    fun getProduct1() {
        viewModelScope.launch {

            when (val response = mainrepository.getProduct1()) {
                is NetworkState.Success -> {
                    pro1.postValue(response.data)
                }
                is NetworkState.Error -> {
                    pro1.postValue(null)
                }
            }
        }
    }

    fun getProduct2(){
        viewModelScope.launch {
            when(val resp2 = mainrepository.getProduct2()){
                is NetworkState.Success -> {
                    pro2.postValue(resp2.data)
                }
                is NetworkState.Error-> {
                    pro2.postValue(null)
                }
            }
        }
    }

    fun getProduct3(){
        viewModelScope.launch {
            when(val res3 = mainrepository.getProduct3()){
                is NetworkState.Success -> pro3.postValue(res3.data)
                is NetworkState.Error -> pro3.postValue(null)
            }
        }
    }

    fun getProduct4(){
        viewModelScope.launch {
            when (val res4 = mainrepository.getProduct4()) {
                is NetworkState.Success -> pro4.postValue(res4.data)
                is NetworkState.Error -> pro4.postValue(null)
            }
        }
    }
}

