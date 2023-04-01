package com.cssun.sudowificracker
import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnL = findViewById<Button>(R.id.buttonL)
        val btnC = findViewById<Button>(R.id.buttonC)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val button = findViewById<Button>(R.id.buttonS)
        val msg = findViewById<TextView>(R.id.vartxt)

        btnC.visibility = View.INVISIBLE // hide the "hack" button
        btnL.visibility = View.INVISIBLE



        val timer1 =
            object : CountDownTimer(8000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // Do nothing while the timer is running
                }

                override fun onFinish() {
                    progressBar.visibility =
                        View.INVISIBLE // hide the progress bar when the timer is finished

                    msg.text = "Preparation Completed"
                    btnC.visibility = View.VISIBLE // show the "hack" button
                }
            }

        button.setOnClickListener {
            msg.text = "Preparing"
            progressBar.visibility =View.VISIBLE // show the progress bar when the button is pressed
            progressBar.isIndeterminate = true // start spinning the progress bar
            timer1.start() // start the countdown timer)
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted, request it
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
            } else {
                // Permission is granted, continue with the operation
                val wifiPopup = WifiPopup.newInstance()
                wifiPopup.show(supportFragmentManager, "wifi_popup")
            }
        }





        val timer2 =
            object : CountDownTimer(45000, 1000) { // 45 seconds countdown, update every second
                override fun onTick(millisUntilFinished: Long) {
                    // Do nothing while the timer is running
                }

                override fun onFinish() {

                    progressBar.visibility =
                        View.INVISIBLE // hide the progress bar when the timer is finished
                    msg.text = "Cracking Completed"

                    btnL.visibility = View.VISIBLE // show the "hack" button
                }
            }



        btnC.setOnClickListener {
            msg.text ="Cracking all the nearby networks"
            progressBar.visibility =
                View.VISIBLE // show the progress bar when the button is pressed
            progressBar.isIndeterminate = true // start spinning the progress bar
            timer2.start() // start the countdown timer
        }


        val timer3 =
            object : CountDownTimer(45000, 1000) { // 45 seconds countdown, update every second
                override fun onTick(millisUntilFinished: Long) {    // Do nothing while the timer is running
                }

                override fun onFinish() {
                    msg.text = "April Fool"
                    progressBar.visibility =
                        View.INVISIBLE // hide the progress bar when the timer is finished

                    val urlIntentf = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            "https://youtu.be/dQw4w9WgXcQ"
                        )
                    )
                    startActivity(urlIntentf)
                }
            }



        btnL.setOnClickListener {
            msg.text = "Making an spreadsheet of passwords"
            progressBar.visibility =
                View.VISIBLE // show the progress bar when the button is pressed
            progressBar.isIndeterminate = true // start spinning the progress bar
            timer3.start() // start the countdown timer                                                                                             }
        }
    }

    val MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 123

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission was granted, show the Wi-Fi popup
                    val wifiPopup = WifiPopup.newInstance()
                    wifiPopup.show(supportFragmentManager, "wifi_popup")
                } else {
                    // Permission denied, handle the error
                    // ...
                    Toast.makeText(this,
                        "Permission denied",

                        Toast.LENGTH_SHORT).show()
                }
                return
            }
            // Handle other permissions if needed
            else -> {
                // ...
            }
        }
    }

}

