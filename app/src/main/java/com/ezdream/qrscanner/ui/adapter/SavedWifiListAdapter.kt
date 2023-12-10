package com.ezdream.qrscanner.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.ezdream.qrscanner.R

class SavedWifiListAdapter(context: Context, data: MutableList<String>) :
    ArrayAdapter<String>(context, R.layout.list_item_wifi_saved, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView

        if (listItemView == null) {
            val inflater = LayoutInflater.from(context)
            listItemView = inflater.inflate(R.layout.list_item_wifi_saved, parent, false)
        }

        val currentItem = getItem(position)

        val textView = listItemView?.findViewById<TextView>(R.id.ssid)
        textView?.text = currentItem

        return listItemView!!
    }
}