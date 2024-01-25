package com.example.admin_buyit.services

import com.example.admin_buyit.models.Product
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    fun getProducts():Call<List<Product>>

    @POST("products")
    fun postProduct(@Body product: Product): Call<Product>

    @PUT("/products/{id}")
    fun updateProduct(@Path("id") productId: Int, @Body product: Product): Call<Product>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<Void>
}