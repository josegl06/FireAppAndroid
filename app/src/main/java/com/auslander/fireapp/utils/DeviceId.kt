package com.auslander.fireapp.utils

import android.provider.Settings

object DeviceId {
    val id: String =
            Settings.Secure.getString(MyApp.context().contentResolver, Settings.Secure.ANDROID_ID)
}