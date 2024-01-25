package com.example.admin_buyit.screens.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.admin_buyit.R
import com.example.admin_buyit.adapters.EditProductAdapter
import com.example.admin_buyit.models.Product
import com.example.admin_buyit.services.AddProduct
import com.example.admin_buyit.services.ApiService
import com.example.admin_buyit.services.FetchProduct
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddProductActivity : AppCompatActivity() {

    private lateinit var editId:EditText
    private lateinit var editTitle:EditText
    private lateinit var editPrice:EditText
    private lateinit var editDescription:EditText
    private lateinit var editImage:EditText
    private lateinit var spinner:Spinner
    private lateinit var backButton:ImageView
    private lateinit var addProduct: Button
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        editId = findViewById(R.id.productIdEt)
        editTitle = findViewById(R.id.productTitleEt)
        editPrice = findViewById(R.id.productPriceEt)
        editDescription = findViewById(R.id.productDescriptionEt)
        editImage = findViewById(R.id.productImgEt)
        spinner = findViewById(R.id.category_spinner)
        backButton = findViewById(R.id.backBtn)
        addProduct = findViewById(R.id.addProductBtn)
        progressBar = findViewById(R.id.progressBar)

        var cat :String  = ""

        val categories = resources.getStringArray(R.array.categories)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        spinner.adapter = adapter

        backButton.setOnClickListener{
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
        }


        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = categories[position]
                cat = selectedCategory
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        addProduct.setOnClickListener{
            progressBar.isVisible = true
            addProduct.isVisible = false

            val product = Product(
                id = editId.text.toString().toInt(),
                title = editTitle.text.toString(),
                price = editPrice.text.toString().toDouble(),
                description = editDescription.text.toString(),
                category = cat,
                image = "https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_.jpg"
            )

            postProduct(product)


        }
    }

    private fun postProduct(product: Product){
        val call: Call<Product> = AddProduct.instance.postProduct(product)

        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    val postedProduct = response.body()
                    Toast.makeText(applicationContext, "$response", Toast.LENGTH_SHORT).show()
                    progressBar.isVisible = false
                    addProduct.isVisible = true
                } else {

                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }


}