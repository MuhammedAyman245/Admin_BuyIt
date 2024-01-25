package com.example.admin_buyit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.admin_buyit.R
import com.example.admin_buyit.models.Product
import com.example.admin_buyit.services.AddProduct
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoveProductAdapter(private var productList:List<Product>): RecyclerView.Adapter<RemoveProductAdapter.RemoveProductAdapterViewHolder>() {


    class RemoveProductAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productTitle: TextView = itemView.findViewById(R.id.productTitle)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val productImage: ImageView = itemView.findViewById(R.id.productImg)
        val removeProduct: ImageView = itemView.findViewById(R.id.remove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoveProductAdapterViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.remove_card, parent, false)
        return  RemoveProductAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: RemoveProductAdapterViewHolder, position: Int) {
        val product = productList[position]

        holder.productTitle.text = product.title
        holder.productPrice.text = "${product.price} $"
        Picasso.get()
            .load(product.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.productImage)
        holder.removeProduct.setOnClickListener{
            val call: Call<Void> = AddProduct.instance.deleteProduct(product.id)

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(holder.itemView.context, "$response", Toast.LENGTH_SHORT).show()
                        notifyDataSetChanged()
                    } else {

                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

    }


}
