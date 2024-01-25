package com.example.admin_buyit.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_buyit.R
import com.example.admin_buyit.models.Product
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.admin_buyit.screens.activities.EditActivity
import com.squareup.picasso.Picasso

class EditProductAdapter (private var productList:List<Product>): RecyclerView.Adapter<EditProductAdapter.ProductViewHolder>() {


    class ProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val productTitle:TextView = itemView.findViewById(R.id.productTitle)
        val productPrice:TextView = itemView.findViewById(R.id.productPrice)
        val productImage: ImageView = itemView.findViewById(R.id.productImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
        return  ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.productTitle.text = product.title
        holder.productPrice.text = "${product.price} $"
        Picasso.get()
            .load(product.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.productImage)
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, EditActivity::class.java)
            intent.putExtra("productTitle",product.title)
            intent.putExtra("productPrice",product.price.toString())
            intent.putExtra("productDesc",product.description)
            intent.putExtra("productImg",product.image)
            intent.putExtra("productCat",product.category)
            intent.putExtra("productId",product.id.toString())
            holder.itemView.context.startActivity(intent)
        }





    }


}
