package com.example.retrophones

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrophones.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var phoneAdapter: PhoneAdapter
    val phone = ArrayList<Phone>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.phoneRv.layoutManager = LinearLayoutManager(this)




        phone.add(Phone("BlackBerry Bold", 3 , 6.9, R.drawable.blackberrybold, "An iconic mobile device that set the standard in its era for design and functionality"))
        phone.add(Phone("Nokia", 2, 4.0, R.drawable.nokiat, "An iconic mobile device that set the standard in its era for design and functionality"))
        phone.add(Phone("Motorola Razr", 4, 4.0, R.drawable.razr, "An iconic mobile device that set the standard in its era for design and functionality"))
        phone.add(Phone("Sony Ericsson  ", 8, 4.0, R.drawable.sonycss, "An iconic mobile device that set the standard in its era for design and functionality"))
        phone.add(Phone("Samsung Galaxy", 10, 4.0, R.drawable.samsanggalaxy, "An iconic mobile device that set the standard in its era for design and functionality"))
        phone.add(Phone(" iPhone ", 20, 5.0, R.drawable.iphones, "An iconic mobile device that set the standard in its era for design and functionality"))
        phone.add(Phone("HTC Desire HD", 20, 4.0, R.drawable.htcd, "An iconic mobile device that set the standard in its era for design and functionality"))
        phone.add(Phone("Nokia N95 ", 9, 4.0, R.drawable.nokian, "An iconic mobile device that set the standard in its era for design and functionality"))
        phone.add(Phone("LG Optimus ", 9, 4.0, R.drawable.lgo, "An iconic mobile device that set the standard in its era for design and functionality"))
        phone.add(Phone("Sony Ericsson Xperia X10 ", 10, 4.0, R.drawable.sonyp, "An iconic mobile device that set the standard in its era for design and functionality"))







        phoneAdapter = PhoneAdapter(phone)
        binding.phoneRv.adapter = phoneAdapter

       phoneAdapter.onClick={


           val intent = Intent(this, DetailActivity::class.java)
           intent.putExtra("name", it.phoneName)
           intent.putExtra("price", it.phonePrice)
           intent.putExtra("quantity", it.phoneQnt)
           intent.putExtra("desc", it.phoneDesc)
           intent.putExtra("image", it.phoneImg)
           startActivity(intent)

        }
        binding.addBtn.setOnClickListener {
            showPhoneAddDialog()

    }
}




















    private fun showPhoneAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.add_phone_layout, null)
        val nameEt = dialogView.findViewById<EditText>(R.id.phoneNameEt)
        val priceEt = dialogView.findViewById<EditText>(R.id.phonePriceEt)
        val qntEt = dialogView.findViewById<EditText>(R.id.phoneQntEt)
        val descEt = dialogView.findViewById<EditText>(R.id.phoneDescEt)

        AlertDialog.Builder(this)
            .setTitle("Add Phone")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEt.text.toString()
                val price = priceEt.text.toString().toDouble()
                val qnt = qntEt.text.toString().toInt()
                val desc = descEt.text.toString()
                val img = R.drawable.blackberrybold
                phone.add(Phone(name, qnt, price, img, desc))
               phoneAdapter.notifyItemInserted(phone.size - 1)
            }
            .setNegativeButton("Cancel", null)
            .show()

    }
}

