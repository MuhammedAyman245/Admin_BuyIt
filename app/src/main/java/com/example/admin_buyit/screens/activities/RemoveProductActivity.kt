package com.example.admin_buyit.screens.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_buyit.R
import com.example.admin_buyit.adapters.EditProductAdapter
import com.example.admin_buyit.adapters.RemoveProductAdapter
import com.example.admin_buyit.models.Product
import com.example.admin_buyit.services.ApiService
import com.example.admin_buyit.services.FetchProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoveProductActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var removeProductAdapter: RemoveProductAdapter
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_product)

        recyclerView = findViewById(R.id.editRecyclerView)
        backButton = findViewById(R.id.backBtn)

        backButton.setOnClickListener{
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
        }
        loadProducts()
    }
    private fun loadProducts() {
        val productService = FetchProduct.buildService(ApiService::class.java)
        val requestCall = productService.getProducts()
        requestCall.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if(response.isSuccessful){
                    val productList = response.body()!!
                    recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
                    removeProductAdapter= RemoveProductAdapter(productList)
                    recyclerView.adapter = removeProductAdapter
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}