package com.clikzop.mvvm_complete.data.network

import com.clikzop.mvvm_complete.ui.auth.model.LoginRequest
import com.clikzop.mvvm_complete.ui.auth.model.LoginResponse
import com.clikzop.mvvm_complete.ui.dashboard.product1.ResposeProduct1
import com.clikzop.mvvm_complete.ui.dashboard.product2.ResponseProduct2
import com.clikzop.mvvm_complete.ui.dashboard.product3.ResponseProduct3
import com.clikzop.mvvm_complete.ui.dashboard.product4.ResponseProduct4
import com.clikzop.mvvm_complete.util.AppConstant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST(AppConstant.loginUrl)
    suspend fun loginCall(
        @Body loginRequest: LoginRequest
    ):Response<LoginResponse>

    @GET(AppConstant.products)
    suspend fun getProduct1():Response<ResposeProduct1>

    @GET(AppConstant.product2)
    suspend fun getProduct2():Response<ResponseProduct2>

    @GET(AppConstant.product3)
    suspend fun getProduct3() : Response<ResponseProduct3>

    @GET(AppConstant.product4)
    suspend fun getProduct4(): Response<ResponseProduct4>


}