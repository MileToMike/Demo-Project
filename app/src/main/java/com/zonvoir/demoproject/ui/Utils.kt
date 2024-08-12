package com.zonvoir.demoproject.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi

import com.zonvoir.demoproject.R


object Utils {




    fun isValidMobile(mobile: String): Boolean {

//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        val mobilePattern = "^(?:0091|\\\\+91|0)[6-9][0-9]{9}$"
        val mobileSecPattern = "[6-9][0-9]{9}$"
        return mobile.matches(mobilePattern.toRegex()) || mobile.matches(mobileSecPattern.toRegex())
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun changeStatusBarColor(context: Activity, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = context.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun getProgressDialog(context: Context): Dialog? {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.loading_progress)
        dialog.setCancelable(false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.window!!.setBackgroundDrawable(context.getDrawable(R.drawable.circle_progress_dialog))
        }
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return dialog
    }
}