package com.ezdream.qrscanner.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ezdream.qrscanner.databinding.ActivityHomeBinding
import com.ezdream.qrscanner.ui.create.CreateActivity
import com.ezdream.qrscanner.ui.scan.ScanActivity
import com.ezdream.qrscanner.ui.wifi.create.WifiCreateActivity
import com.ezdream.qrscanner.ui.wifi.saved.WifiSavedActivity
import com.ezdream.qrscanner.util.QRUtil.isUrl
import com.ezdream.qrscanner.util.QRUtil.openLinkInBrowser
import com.ramotion.circlemenu.CircleMenuView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("TAG", "onCreate: HomeActivity")

        binding = ActivityHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[HomeActivityVM::class.java]
        setContentView(binding.root)


        binding.circleMenuView.eventListener = object : CircleMenuView.EventListener() {

            override fun onButtonClickAnimationEnd(view: CircleMenuView, buttonIndex: Int) {
                when (buttonIndex) {
                    0 -> {
                        val intent = Intent(this@HomeActivity, ScanActivity::class.java)
                        startActivityForResult(intent, 100)
                        overridePendingTransition(
                            com.ezdream.qrscanner.R.anim.fade_in,
                            com.ezdream.qrscanner.R.anim.fade_out
                        )
                    }

                    1 -> {
                        val intent = Intent(this@HomeActivity, CreateActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(
                            com.ezdream.qrscanner.R.anim.fade_in,
                            com.ezdream.qrscanner.R.anim.fade_out
                        )
                    }

                    2 -> {
                        val intent = Intent(this@HomeActivity, WifiSavedActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(
                            com.ezdream.qrscanner.R.anim.fade_in,
                            com.ezdream.qrscanner.R.anim.fade_out
                        )
                    }

                    3 -> {
                        val intent = Intent(this@HomeActivity, WifiCreateActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(
                            com.ezdream.qrscanner.R.anim.fade_in,
                            com.ezdream.qrscanner.R.anim.fade_out
                        )
                    }

                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            val scannedData = data?.getStringExtra("scannedData")
            if (scannedData != null) {
                Log.i("PreviousActivity", "Scanned Data: $scannedData")
                if (isUrl(scannedData)) {
                    openLinkInBrowser(scannedData, this@HomeActivity)
                    viewModel.saveLink(scannedData)
                }
            } else {
                // Veri alınamadı
            }
        }
    }
}