package com.clikzop.mvvm_complete.ui.dashboard.product4

data class ResponseProduct4(
    val limit: Int,
    val products: List<Product4Data>,
    val skip: Int,
    val total: Int
)