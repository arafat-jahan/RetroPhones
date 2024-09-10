package com.example.retrophones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.retrophones.databinding.PhoneItemBinding

class PhoneAdapter(private val phoneList:ArrayList<Phone>): RecyclerView.Adapter<PhoneAdapter.MyViewHolder>() {
    var onClick: ((Phone) -> Unit)? = null

    class MyViewHolder(val binding: PhoneItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = PhoneItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {

        return phoneList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.apply {
            phoneName.text = phoneList[position].phoneName
            phoneQnt.text = "Quantity: " + phoneList[position].phoneQnt.toString()
            phonePrice.text = "Price: $" + phoneList[position].phonePrice.toString()
            phoneImg.setImageResource(phoneList[position].phoneImg)


        }

        holder.itemView.setOnClickListener {
            onClick?.invoke(phoneList[position])
        }

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete Fruit Item")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes") { _, _ ->
                    phoneList.removeAt(position)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("No", null)
                .show()
            true
        }


    }


}

