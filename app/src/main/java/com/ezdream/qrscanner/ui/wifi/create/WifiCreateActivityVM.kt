package com.ezdream.qrscanner.ui.wifi.create

import androidx.lifecycle.ViewModel
import com.ezdream.qrscanner.util.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WifiCreateActivityVM @Inject constructor(private val sharedPreferencesHelper: SharedPreferencesHelper) :
    ViewModel() {

    fun saveWifi(ssid: String, content: String) {
        sharedPreferencesHelper.saveString(ssid, content)
    }

}