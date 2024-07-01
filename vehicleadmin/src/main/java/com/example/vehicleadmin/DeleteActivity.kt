package com.example.vehicleadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vehicleadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDelete.setOnClickListener {
            val etvehicleNumber = binding.etDeleteVehicleNumber.text.toString()
            if (etvehicleNumber.isNotEmpty()){
                deleteData(etvehicleNumber)
            } else {
                Toast.makeText(this, "Enter Vehicle Number to Delete", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun deleteData(vehicleNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Info")
        databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
            binding.etDeleteVehicleNumber.text.clear()
            Toast.makeText(this, "Deteleted Successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}