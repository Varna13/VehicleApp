package com.example.vehicleadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vehicleadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var updateBinding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateBinding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(updateBinding.root)

        updateBinding.btnUpdate.setOnClickListener {
            val etUpdateOwnerName = updateBinding.etUpdateOwnerName.text.toString()
            val etUpdateVehicleRTO = updateBinding.etUpdateVehicleRTO.text.toString()
            val etUpdateVehicleBrand = updateBinding.etUpdateVehicleBrand.text.toString()
            val etUpdateVehicleNumber = updateBinding.etUpdateVehicleNumber.text.toString()
            updateData(etUpdateVehicleNumber, etUpdateOwnerName, etUpdateVehicleBrand, etUpdateVehicleRTO)
        }
    }

    fun updateData(vehicleNumber: String, ownerName: String, vehicleBrand: String, vehicleRto: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Info")
        val vehicleData = mapOf<String, String>("ownerName" to ownerName, "vehicleBrand" to vehicleBrand, "vehicleRto" to vehicleRto)
        databaseReference.child(vehicleNumber).updateChildren(vehicleData).addOnSuccessListener {
            updateBinding.etUpdateOwnerName.text.clear()
            updateBinding.etUpdateVehicleBrand.text.clear()
            updateBinding.etUpdateVehicleRTO.text.clear()
            updateBinding.etUpdateVehicleNumber.text.clear()

            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Can't update", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}