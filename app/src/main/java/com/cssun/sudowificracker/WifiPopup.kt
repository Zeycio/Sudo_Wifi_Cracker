package com.cssun.sudowificracker

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

class WifiPopup : DialogFragment() {

    private val MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 123
    private lateinit var wifiManager: WifiManager
    private lateinit var wifiListAdapter: ArrayAdapter<String>
    private lateinit var wifiList: ListView
    private lateinit var mActivity: FragmentActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentActivity) {
            mActivity = context
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.popup_layout, container, false)

        wifiManager =
            mActivity.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiList = view.findViewById(R.id.wifi_list)

        if (ContextCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                mActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        } else {
            // Permission is granted, continue with the operation
            val scanResults = wifiManager.scanResults
            val wifiNames = mutableListOf<String>()
            for (scanResult: ScanResult in scanResults) {
                wifiNames.add(scanResult.SSID)
            }

            wifiListAdapter =
                ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1, wifiNames)
            wifiList.adapter = wifiListAdapter
        }

        val closeButton = view.findViewById<Button>(R.id.close_button)
        closeButton.setOnClickListener {
            dismiss()
        }

        return view
    }

    companion object {
        fun newInstance(): WifiPopup {
            return WifiPopup()
        }
    }
}