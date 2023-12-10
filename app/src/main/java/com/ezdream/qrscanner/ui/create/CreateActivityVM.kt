package com.ezdream.qrscanner.ui.create

import androidx.lifecycle.ViewModel
import com.ezdream.qrscanner.util.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CreateActivityVM @Inject constructor(private val sharedPreferencesHelper: SharedPreferencesHelper) : ViewModel() {

    fun saveLink(content: String) {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH) + 1
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        sharedPreferencesHelper.saveString("$day.$month.$year QR Code Created", content)
    }

}