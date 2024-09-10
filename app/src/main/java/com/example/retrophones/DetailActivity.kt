package com.example.retrophones

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.retrophones.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name") ?: "Unknown Phone"
        val price = intent.getDoubleExtra("price", 0.0)
        val qnt = intent.getIntExtra("quantity", 0)
        val img = intent.getIntExtra("image", R.drawable.blackberrybold)


        val desc = intent.getStringExtra("desc") ?: "No description available."

        Log.d("DetailActivity", "Name: $name")
        Log.d("DetailActivity", "Price: $price")
        Log.d("DetailActivity", "Quantity: $qnt")
        Log.d("DetailActivity", "Image Resource ID: $img")
        Log.d("DetailActivity", "Description: $desc")

        binding.apply {
            phoneName.text = name
            phonePrice.text = "Price: $$price"
            phoneQnt.text = "Quantity: $qnt"
            phoneImg.setImageResource(img)
            phoneDesc.text = desc
        }
    }
}
