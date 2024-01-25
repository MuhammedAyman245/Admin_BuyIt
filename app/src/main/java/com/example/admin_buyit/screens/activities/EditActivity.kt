package com.example.admin_buyit.screens.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.admin_buyit.R
import com.example.admin_buyit.models.Product
import com.example.admin_buyit.services.AddProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {
    private lateinit var editTitle: EditText
    private lateinit var editPrice: EditText
    private lateinit var editDescription: EditText
    private lateinit var editImage: EditText
    private lateinit var backButton: ImageView
    private lateinit var editProduct: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        editTitle = findViewById(R.id.productTitleEt)
        editPrice = findViewById(R.id.productPriceEt)
        editDescription = findViewById(R.id.productDescriptionEt)
        editImage = findViewById(R.id.productImgEt)
        backButton = findViewById(R.id.backBtn)
        editProduct = findViewById(R.id.editProductBtn)
        progressBar = findViewById(R.id.progressBar)

        backButton.setOnClickListener{
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
        }

        val productId = intent.getStringExtra("productId")
        val productCat = intent.getStringExtra("productCat")
        Toast.makeText(applicationContext, "$productId,$productCat", Toast.LENGTH_SHORT).show()

        editProduct.setOnClickListener{
            progressBar.isVisible = true
            editProduct.isVisible = false

            val product = Product(
                id = productId!!.toInt(),
                title = editTitle.text.toString(),
                price = editPrice.text.toString().toDouble(),
                description = editDescription.text.toString(),
                category = productCat!!,
                image = "https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_.jpg"
            )
            updateProduct(productId!!.toInt(),product)
        }

    }

    private fun updateProduct(id:Int, product: Product){
        val call: Call<Product> = AddProduct.instance.updateProduct(id, product)
        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    val updatedProduct = response.body()
                    Toast.makeText(applicationContext, "$updatedProduct", Toast.LENGTH_SHORT).show()
                    progressBar.isVisible = false
                    editProduct.isVisible = true
                } else {

                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}