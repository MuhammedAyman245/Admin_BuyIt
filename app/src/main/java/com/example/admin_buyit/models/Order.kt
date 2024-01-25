package com.example.admin_buyit.models

data class Order(
    val orderId:String,
    val orderTitle:String,
    val numOfItems: String,
    val totalPrice:String,
    val userLocation:String,
)