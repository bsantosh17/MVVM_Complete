package com.clikzop.mvvm_complete.data.repository

import android.util.Log
import com.clikzop.mvvm_complete.data.network.ApiService
import com.clikzop.mvvm_complete.data.network.NetworkState
import com.clikzop.mvvm_complete.ui.auth.model.LoginRequest
import com.clikzop.mvvm_complete.ui.auth.model.LoginResponse
import com.clikzop.mvvm_complete.ui.dashboard.product1.ResposeProduct1
import com.clikzop.mvvm_complete.ui.dashboard.product2.ResponseProduct2
import com.clikzop.mvvm_complete.ui.dashboard.product3.ResponseProduct3
import com.clikzop.mvvm_complete.ui.dashboard.product4.ResponseProduct4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Mainrepository(val apiService: ApiService) {
    suspend fun loginApi(loginRequest: LoginRequest): NetworkState<LoginResponse> {

        return try {
            val loginRes = apiService.loginCall(loginRequest)
            val responseBody = loginRes.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error("Response Body is Null")
            }
        } catch (e: Exception) {
            NetworkState.Error("Network Error...${e.message}")
        }
    }

    suspend fun getProduct1() = withContext(Dispatchers.IO){
        delay(8000)
         val response1 = apiService.getProduct1()
          if (response1.isSuccessful) {
            val resBody1 = response1.body()
            if (resBody1 != null) {
                NetworkState.Success(resBody1)
            } else {
                NetworkState.Error("Response is Null")
            }
        }else{
            NetworkState.Error("Error $response1")
        }
    }

    suspend fun getProduct2() = withContext(Dispatchers.IO){
        delay(2000)
        val response2 = apiService.getProduct2()
          if(response2.isSuccessful){
            val res2 = response2.body()
            if(res2 != null){
                NetworkState.Success(res2)
            }else{
                NetworkState.Error("Response is Null")
            }
        }else{
            NetworkState.Error("Error $response2")
        }
    }

    suspend fun getProduct3() = withContext(Dispatchers.IO){
        delay(6000)
        val res3 = apiService.getProduct3()
         if(res3.isSuccessful){
            val respos3 = res3.body()
            if(respos3 != null){
                NetworkState.Success(respos3)
            }else{
                NetworkState.Error("Response id Null")
            }
        }else{
            NetworkState.Error("Error $res3")
        }
    }

    suspend fun getProduct4() = withContext(Dispatchers.IO){
        delay(4000)
       val r4 = apiService.getProduct4()
         if(r4.isSuccessful){
            val res4 = r4.body()
            if(res4 != null){
                NetworkState.Success(res4)
            }else{
                NetworkState.Error("Response Is Null")
            }
        }else{
            NetworkState.Error("Response $r4")
        }
    }

}