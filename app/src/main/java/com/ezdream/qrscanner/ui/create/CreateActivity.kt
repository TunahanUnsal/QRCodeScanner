package com.ezdream.qrscanner.ui.create

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ezdream.qrscanner.databinding.ActivityCreateBinding
import com.ezdream.qrscanner.util.ImageUtil.getImageUri
import com.ezdream.qrscanner.util.ImageUtil.shareBitmap
import com.ezdream.qrscanner.util.QRUtil.generateQRCode
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream


@AndroidEntryPoint
class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding
    private lateinit var viewModel: CreateActivityVM
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("TAG", "onCreate: CreateActivity")

        binding = ActivityCreateBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[CreateActivityVM::class.java]
        setContentView(binding.root)

        binding.createButton.setOnClickListener {
            val bitmapTemp: Bitmap? = generateQRCode(binding.editText.text.toString(), 150, 150)
            if (bitmapTemp != null) {
                bitmap = bitmapTemp
                binding.qr.setImageBitmap(bitmap)
                binding.card.visibility = View.VISIBLE
            }
        }

        binding.card.setOnClickListener {
            shareBitmap(bitmap, this)
        }

    }
}