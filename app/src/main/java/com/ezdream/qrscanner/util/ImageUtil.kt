package com.ezdream.qrscanner.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream

object ImageUtil {

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "QR Code", null)
        return Uri.parse(path)
    }

    fun shareBitmap(bitmap: Bitmap, context: Context) {
        val i = Intent(Intent.ACTION_SEND)

        i.type = "image/*"
        i.putExtra(
            Intent.EXTRA_STREAM,
            getImageUri(context, bitmap)
        )
        try {
            context.startActivity(Intent.createChooser(i, "Share QR Code"))
        } catch (ex: ActivityNotFoundException) {
            ex.printStackTrace()
        }
    }
}