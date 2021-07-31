package com.manuelcarvalho.weatherapp.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity

private const val TAG = "utils"
fun sendEmail(context: Context, desc: String) {


    var stringList = ""


    val to = "tom@gmail.com"
    val subject = "Weather for today"
    val message = stringList

    val intent = Intent(Intent.ACTION_SEND)
    val addressees = arrayOf(to)
    intent.putExtra(Intent.EXTRA_EMAIL, addressees)
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    intent.putExtra(Intent.EXTRA_TEXT, message)
    intent.type = "message/rfc822"
    startActivity(context, Intent.createChooser(intent, "Select Email Sending App :"), null)


}


