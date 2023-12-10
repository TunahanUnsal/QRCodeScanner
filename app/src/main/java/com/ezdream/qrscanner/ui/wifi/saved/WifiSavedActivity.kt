package com.ezdream.qrscanner.ui.wifi.saved

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ezdream.qrscanner.databinding.ActivitySavedWifiBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WifiSavedActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySavedWifiBinding
    private lateinit var viewModel: WifiSavedActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("TAG", "onCreate: WifiSavedActivity")

        binding = ActivitySavedWifiBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[WifiSavedActivityVM::class.java]
        setContentView(binding.root)

        viewModel.getAllSavedWifi(this, binding.list, this)
        viewModel.listItemListener(binding.list, binding.qr, this,binding.card,binding.linkButton,applicationContext)

    }
}