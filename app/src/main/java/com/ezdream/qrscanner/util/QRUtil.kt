package com.ezdream.qrscanner.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.qrcode.QRCodeWriter

object QRUtil {

    fun generateQRCode(content: String, width: Int, height: Int): Bitmap? {
        val qrCodeWriter = QRCodeWriter()
        try {
            val bitMatrix: BitMatrix =
                qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height)
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(
                        x,
                        y,
                        if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt()
                    )
                }
            }
            return bmp
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return null
    }

    fun readQRCode(activity: Activity) {
        val integrator = IntentIntegrator(activity)
        integrator.setOrientationLocked(true)
        integrator.setTimeout(20000)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.initiateScan()
    }

    fun isUrl(str: String): Boolean {
        val regex = Regex(
            "(http://|https://)?([w]{3}\\.)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}(/[^/]*)*"
        )
        return regex.matches(str)
    }

    fun openLinkInBrowser(url: String, context: Context) {
        var temp = url
        if (!temp.startsWith("http://") && !temp.startsWith("https://")) {
            temp = "http://$url"
        }

        val sharingIntent = Intent(Intent.ACTION_VIEW)
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        sharingIntent.data = Uri.parse(temp)

        val chooserIntent = Intent.createChooser(sharingIntent, "Open With")
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        startActivity(context, chooserIntent, null)
    }

    fun wifiQRCreate(ssid: String, password: String): Pair<String, Bitmap?> {
        val text = "WIFI:T:WPA;S:$ssid;P:$password;H:;;"
        val bitmap = generateQRCode(text, 150, 150)
        return Pair(text, bitmap)
    }

}