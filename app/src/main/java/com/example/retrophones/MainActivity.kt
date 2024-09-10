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




        phone.add(Phone("BlackBerry Bold", 3 , 20.9, R.drawable.blackberrybold, "A landmark device that redefined smartphone design with its sleek look and efficient keyboard, setting benchmarks in its time."))
        phone.add(Phone("Nokia", 2, 40.0, R.drawable.nokiat, "Known for its robust build and reliable performance, this phone was a staple in mobile communication during its peak"))
        phone.add(Phone("Motorola Razr", 40, 14.0, R.drawable.razr, "Famous for its iconic flip design, the Razr was a trendsetter in the world of clamshell phones with its stylish and compact form."))
        phone.add(Phone("Sony Ericsson  ", 18, 40.0, R.drawable.sonycss, "A blend of elegance and functionality, this model offered a high-quality multimedia experience with a focus on design"))
        phone.add(Phone("Samsung Galaxy", 10, 24.0, R.drawable.samsanggalaxy, "A game-changer in the smartphone market, offering a stunning display and advanced features that set new standards for mobile technology."))
        phone.add(Phone(" iPhone ", 20, 5.0, R.drawable.iphones, "Apple's revolutionary device that transformed the smartphone industry with its intuitive interface and powerful performance."))
        phone.add(Phone("HTC Desire HD", 20, 34.0, R.drawable.htcd, "Known for its large, vivid display and solid performance, this smartphone offered a premium experience at its time."))
        phone.add(Phone("Nokia N95 ", 9, 44.0, R.drawable.nokian, "An iconic mobile device that set the standard in its era for design and functionality"))
        phone.add(Phone("LG Optimus ", 9, 54.0, R.drawable.lgo, "An innovative phone featuring advanced multimedia capabilities and a versatile design that catered to both work and play"))
        phone.add(Phone("Sony Ericsson Xperia X10 ", 40, 4.0, R.drawable.sonyp, "A well-rounded smartphone with a focus on user-friendly features and reliable performance, making it a popular choice for many."))
        phone.add(Phone("Motorola Droid", 7, 55.0, R.drawable.motoroladroid, "A high-end phone that delivered a top-notch user experience with its impressive screen and advanced features"))
        phone.add(Phone("HTC One", 15, 14.7, R.drawable.htcd, "A pioneering Android phone that combined a full QWERTY keyboard with a large touchscreen, making it a favorite for productivity."))
        phone.add(Phone("Nokia Lumia 920", 12, 24.5, R.drawable.sonycss, "Renowned for its premium build quality and stunning display, the HTC One offered a high-end smartphone experience."))
        phone.add(Phone("BlackBerry Curve", 5, 33.2, R.drawable.bbc, "A beloved model known for its practical physical keyboard and business-friendly features, making it a favorite among professionals"))
        phone.add(Phone("Samsung Note", 18, 15.3, R.drawable.samsunggalaxynote, "A groundbreaking phablet that bridged the gap between smartphones and tablets, offering a large screen and versatile functionality."))






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


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                phone.removeAt(viewHolder.adapterPosition)
                phoneAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.phoneRv)



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

