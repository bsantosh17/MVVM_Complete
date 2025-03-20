package com.clikzop.mvvm_complete.data.repository

import com.clikzop.mvvm_complete.data.network.ApiService
import com.clikzop.mvvm_complete.data.network.NetworkState
import com.clikzop.mvvm_complete.ui.auth.model.LoginRequest
import com.clikzop.mvvm_complete.ui.auth.model.LoginResponse

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

}