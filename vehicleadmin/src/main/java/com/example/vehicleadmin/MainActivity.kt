package com.example.vehicleadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vehicleadmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnUpload.setOnClickListener {
            startActivity(Intent(this, UploadActivity::class.java))
            finish()
        }

        binding.btnUpdate.setOnClickListener {
            startActivity(Intent(this, UpdateActivity::class.java))
            finish()
        }

        binding.btnDelete.setOnClickListener {
            startActivity(Intent(this, DeleteActivity::class.java))
            finish()
        }
    }
}