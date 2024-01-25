package com.example.admin_buyit.screens.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.admin_buyit.R

class HomeActivity : AppCompatActivity() {
    private lateinit var addProduct:CardView
    private lateinit var editProduct:CardView
    private lateinit var removeProduct:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        addProduct = findViewById(R.id.addProduct)
        editProduct = findViewById(R.id.editProduct)
        removeProduct = findViewById(R.id.removeProduct)

        addProduct.setOnClickListener{
            val intent = Intent(applicationContext, AddProductActivity::class.java)
            startActivity(intent)
        }
        editProduct.setOnClickListener{
            val intent = Intent(applicationContext, EditProductActivity::class.java)
            startActivity(intent)
        }
        removeProduct.setOnClickListener{
            val intent = Intent(applicationContext, RemoveProductActivity::class.java)
            startActivity(intent)
        }
    }
}