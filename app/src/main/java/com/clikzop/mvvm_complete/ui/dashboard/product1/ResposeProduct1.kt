package com.clikzop.mvvm_complete.ui.dashboard.product1

data class ResposeProduct1(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)