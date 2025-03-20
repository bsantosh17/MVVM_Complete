package com.clikzop.mvvm_complete.data.network

sealed class NetworkState<out T>{
    data class Success<out T>(val data:T):NetworkState<T>()
    data class Error<out T>(val message:String,val data:T? =null):NetworkState<T>()
}
