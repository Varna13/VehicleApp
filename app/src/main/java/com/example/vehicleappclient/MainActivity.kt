package com.example.vehicleappclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vehicleappclient.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //read from firebase - client side
        binding.btnSearch.setOnClickListener {
            val vehicleNumber = binding.etVehicleNumber.text.toString()
            if (vehicleNumber.isNotEmpty()){
                readData(vehicleNumber)

            } else {
                Toast.makeText(this, "Plaese Enter the vehicle number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(vehicleNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Info")
        databaseReference.child(vehicleNumber).get().addOnSuccessListener {
            if (it.exists()){
                val ownerName = it.child("ownerName").value
                val vehicleBrand = it.child("vehicleBrand").value
                val vehicleRto = it.child("vehicleRto").value
                Toast.makeText(this, "Results Found", Toast.LENGTH_SHORT).show()
                binding.etVehicleNumber.text.clear()

                binding.tvReadOwnerName.text = ownerName.toString()
                binding.tvReadVehicleBrand.text = vehicleBrand.toString()
                binding.tvReadVechicleRTO.text = vehicleRto.toString()
            } else {
                Toast.makeText(this, "Vehicle Number does not exists", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show()

        }
    }
}