package com.clikzop.mvvm_complete.data.network

import com.clikzop.mvvm_complete.ui.auth.model.LoginRequest
import com.clikzop.mvvm_complete.ui.auth.model.LoginResponse
import com.clikzop.mvvm_complete.util.AppConstant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(AppConstant.loginUrl)
    suspend fun loginCall(
        @Body loginRequest: LoginRequest
    ):Response<LoginResponse>
}