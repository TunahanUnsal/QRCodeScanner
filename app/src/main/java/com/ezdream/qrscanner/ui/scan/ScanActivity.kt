package com.ezdream.qrscanner.ui.scan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ezdream.qrscanner.databinding.ActivityHomeBinding
import com.ezdream.qrscanner.databinding.ActivityScanBinding
import com.ezdream.qrscanner.util.QRUtil.readQRCode
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding
    private lateinit var viewModel: ScanActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("TAG", "onCreate: ScanActivity")

        binding = ActivityScanBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ScanActivityVM::class.java]
        setContentView(binding.root)

        readQRCode(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            val scannedText = result.contents
            Log.i("Scan", "onActivityResult: $scannedText")
            val resultIntent = Intent()
            resultIntent.putExtra("scannedData", scannedText)
            setResult(Activity.RESULT_OK, resultIntent)
        } else {
            val resultIntent = Intent()
            resultIntent.putExtra("scannedData", "")
            setResult(Activity.RESULT_OK, resultIntent)
        }

        finish()
    }
}