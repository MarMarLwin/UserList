package com.mml.userapp.common

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

class Utils {

    private var myPermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET
    )
    fun hasPermission(context: Context, vararg permissions: String): Boolean = permissions.all()
    {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestAppPermission(context: Context, selectPermission: Array<String>) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermission(context, *myPermissions)) {
                ActivityCompat.requestPermissions(context as Activity, selectPermission, 1)
            }
        }
    }
}