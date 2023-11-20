package com.auslander.fireapp.utils.network

import android.app.Activity
import com.auslander.fireapp.utils.MyApp
import com.google.firebase.auth.PhoneAuthProvider
import io.michaelrocks.libphonenumber.android.NumberParseException
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import io.michaelrocks.libphonenumber.android.Phonenumber
import java.util.concurrent.TimeUnit

import android.util.Log

class AuthManager {
    fun verify(phoneNumber: String, activity: Activity, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks) {
        Log.e("AuthManager", "Iniciando verificación para el número: $phoneNumber")

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            activity, // Activity (for callback binding)
            callbacks
        )
    }

    fun formatNumber(number: String, countryCode: String): String? {
        val context = MyApp.context()
        val util = PhoneNumberUtil.createInstance(context)
        var phone: String? = null

        try {
            val phoneNumber = util.parse(number, countryCode)
            phone = util.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164)
            Log.d("AuthManager", "Número formateado: $phone")
        } catch (e: NumberParseException) {
            Log.e("AuthManager", "Error al formatear el número", e)
        }

        return phone
    }
}
