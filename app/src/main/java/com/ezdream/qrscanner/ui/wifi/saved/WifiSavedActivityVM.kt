package com.ezdream.qrscanner.ui.wifi.saved

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import com.ezdream.qrscanner.ui.adapter.SavedWifiListAdapter
import com.ezdream.qrscanner.util.ImageUtil
import com.ezdream.qrscanner.util.QRUtil
import com.ezdream.qrscanner.util.QRUtil.isUrl
import com.ezdream.qrscanner.util.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WifiSavedActivityVM @Inject constructor(private val sharedPreferencesHelper: SharedPreferencesHelper) :
    ViewModel() {

    private val listKey: MutableList<String> = mutableListOf()
    private val listValue: MutableList<String> = mutableListOf()
    private lateinit var adapter: SavedWifiListAdapter
    private var selected: Int = 0

    fun getAllSavedWifi(context: Context, listView: ListView, activity: Activity) {
        val allData: Map<String, String> =
            sharedPreferencesHelper.getAllSharedPreferences() as Map<String, String>

        for ((key, value) in allData) {
            listKey.add(key)
            listValue.add(value)
        }

        adapter = SavedWifiListAdapter(context, listKey)
        activity.runOnUiThread {
            listView.adapter = adapter
        }

    }

    fun listItemListener(
        listView: ListView,
        imageView: ImageView,
        activity: Activity,
        cardView: CardView,
        imageButton: ImageButton,
        context: Context
    ) {

        listView.setOnItemClickListener { _, _, position, _ ->
            selected = position
            activity.runOnUiThread {
                imageView.setImageBitmap(QRUtil.generateQRCode(listValue[position], 150, 150))
                cardView.visibility = View.VISIBLE
                if(isUrl(listValue[position])){
                    imageButton.visibility = View.VISIBLE
                }else{
                    imageButton.visibility = View.INVISIBLE
                }

            }
        }

        cardView.setOnClickListener {
            QRUtil.generateQRCode(listValue[selected], 150, 150)
                ?.let { it1 -> ImageUtil.shareBitmap(it1, activity) }
        }

        imageButton.setOnClickListener {
            QRUtil.openLinkInBrowser(listValue[selected],context)
        }

    }

}