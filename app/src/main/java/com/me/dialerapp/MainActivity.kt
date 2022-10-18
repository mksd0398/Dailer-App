package com.me.dialerapp

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.me.dialerapp.databinding.ActivityMainBinding
import android.Manifest.permission.CALL_PHONE

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // beauty of kotlin
        // Initialize Buttons without creating objects
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)
        binding.btnZero.setOnClickListener(this)
        binding.btnSave.setOnClickListener(this)
        binding.btnHash.setOnClickListener(this)
        binding.btnCall.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
        binding.btnAsterisk.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.btnZero -> {
                onDigit("0")
            }
            binding.btn1 -> {
                onDigit("1")
            }
            binding.btn2 -> {
                onDigit("2")
            }
            binding.btn3 -> {
                onDigit("3")
            }
            binding.btn4 -> {
                onDigit("4")
            }
            binding.btn5 -> {
                onDigit("5")
            }
            binding.btn6 -> {
                onDigit("6")
            }
            binding.btn7 -> {
                onDigit("7")
            }
            binding.btn8 -> {
                onDigit("8")
            }
            binding.btn9 -> {
                onDigit("9")
            }

            binding.btnAsterisk -> {
                onDigit("*")
            }

            binding.btnCall -> {

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:${binding.input.text}")

                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity, CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                startActivity(callIntent)
            }

            binding.btnSave -> {
                val intent = Intent(ContactsContract.Intents.Insert.ACTION)
                intent.type = ContactsContract.RawContacts.CONTENT_TYPE
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, binding.input.text.toString())
                getResult.launch(intent)
            }

            binding.btnHash -> {
                onDigit("#")
            }

            binding.btnDelete -> {
                binding.input.text.clear()
            }

        }
    }

    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        var msg = ""
        if (it.resultCode == Activity.RESULT_OK) {
            msg = "Added Contact"
        }
        if (it.resultCode == Activity.RESULT_CANCELED) {
            msg = "Cancelled Added Contact"
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun onDigit(str: String) {
        binding.input.append(str)
    }


}