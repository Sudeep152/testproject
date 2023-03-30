package com.shashank.soketimp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.shashank.soketimp.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityFirstBinding

    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    private val requestcode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstBinding.inflate(layoutInflater)
        val view  =binding.root;
        setContentView(view)

        if (!isPermissionGranted()) {
            askPermissions()
        }
        binding.loginBtn.setOnClickListener {

            Toast.makeText(this, "hel", Toast.LENGTH_SHORT).show()
            val name = binding.usernameEdit.text.toString()


            val inten = Intent(this,MainActivity::class.java)
            inten.putExtra("myName",name)
            startActivity(inten)

        }



    }
    private fun askPermissions() {
        ActivityCompat.requestPermissions(this, permissions, requestcode)
    }

    private fun isPermissionGranted(): Boolean {

        permissions.forEach {
            if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
                return false
        }

        return true
    }
}