package com.ezdream.qrscanner.ui.wifi.create

import android.graphics.Bitmap
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.ezdream.qrscanner.R
import com.ezdream.qrscanner.databinding.ActivityCreateWifiBinding
import com.ezdream.qrscanner.util.ImageUtil.shareBitmap
import com.ezdream.qrscanner.util.QRUtil.wifiQRCreate
import com.ezdream.qrscanner.util.UiUtil.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WifiCreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateWifiBinding
    private lateinit var viewModel: WifiCreateActivityVM
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("TAG", "onCreate: WifiCreateActivity")

        binding = ActivityCreateWifiBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[WifiCreateActivityVM::class.java]
        setContentView(binding.root)

        binding.createButton.isEnabled = true
        binding.createButton.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.wand_green
            )
        )

        binding.createButton.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.wand_gray
            )
        )

        binding.ssid.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEditTexts()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEditTexts()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.createButton.setOnClickListener {

            val (content, bitmapTemp) = wifiQRCreate(
                binding.ssid.text.toString().trim(),
                binding.password.text.toString().trim()
            )
            if (bitmapTemp != null) {
                bitmap = bitmapTemp
                binding.qr.setImageBitmap(bitmap)
                binding.card.visibility = View.VISIBLE
                viewModel.saveWifi(binding.ssid.text.toString().trim(), content)
                hideKeyboard(this)
            }
        }

        binding.card.setOnClickListener {
            shareBitmap(bitmap, this)
        }

    }

    fun checkEditTexts() {
        val text1 = binding.ssid.toString().trim()
        val text2 = binding.password.text.toString().trim()

        if (text1.isNotEmpty() && text2.isNotEmpty()) {
            binding.createButton.isEnabled = true
            binding.createButton.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.wand_green
                )
            )
        } else {
            binding.createButton.isEnabled = false
            binding.createButton.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.wand_gray
                )
            )
        }
    }

}