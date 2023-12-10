package com.ezdream.qrscanner.ui.create

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
import com.ezdream.qrscanner.databinding.ActivityCreateBinding
import com.ezdream.qrscanner.util.ImageUtil.shareBitmap
import com.ezdream.qrscanner.util.QRUtil.generateQRCode
import com.ezdream.qrscanner.util.QRUtil.isUrl
import com.ezdream.qrscanner.util.UiUtil.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint


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

        binding.createButton.isEnabled = false
        binding.createButton.setImageDrawable(
            AppCompatResources.getDrawable(
                this,
                R.drawable.wand_gray
            )
        )

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (isUrl(s.toString())) {
                    binding.createButton.isEnabled = true
                    binding.createButton.setImageDrawable(
                        AppCompatResources.getDrawable(
                            this@CreateActivity,
                            R.drawable.wand_green
                        )
                    )
                } else {
                    binding.createButton.isEnabled = false
                    binding.createButton.setImageDrawable(
                        AppCompatResources.getDrawable(
                            this@CreateActivity,
                            R.drawable.wand_gray
                        )
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.createButton.setOnClickListener {
            val bitmapTemp: Bitmap? = generateQRCode(binding.editText.text.toString(), 150, 150)
            if (bitmapTemp != null) {
                bitmap = bitmapTemp
                binding.qr.setImageBitmap(bitmap)
                binding.card.visibility = View.VISIBLE
                hideKeyboard(this)
                viewModel.saveLink(binding.editText.text.toString())
            }
        }

        binding.card.setOnClickListener {
            shareBitmap(bitmap, this)
        }

    }
}