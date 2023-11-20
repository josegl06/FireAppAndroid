package com.auslander.fireapp.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.auslander.fireapp.R
import com.auslander.fireapp.activities.authentication.AuthenticationActivity
import com.auslander.fireapp.databinding.ActivityAgreePrivacyPolicyBinding
import com.auslander.fireapp.utils.DetachableClickListener
import com.auslander.fireapp.utils.PermissionsUtil
import com.auslander.fireapp.utils.SharedPreferencesManager
import com.auslander.fireapp.utils.network.FireManager


class AgreePrivacyPolicyActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 451
    private lateinit var binding: ActivityAgreePrivacyPolicyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgreePrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAgree.setOnClickListener {

            showContactsConfirmationDialog()

        }


    }

    private fun showContactsConfirmationDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Agreement")
        dialog.setCancelable(false)


        val view = LayoutInflater.from(this).inflate(R.layout.privacy_policy_dialog, null, false)
        dialog.setView(view)

        val tv = view.findViewById<TextView>(R.id.tv_privacy_policy_dialog)

        val checkBox = view.findViewById<CheckBox>(R.id.chb_agree)
        checkBox.text =
            "By Checking this, You agree to the collection and use of information in accordance with this Privacy Policy"


        getHtml4(tv)

        dialog.setNegativeButton("DECLINE", null)

        dialog.setPositiveButton("AGREE") { dialog, which ->
            SharedPreferencesManager.setAgreedToPrivacyPolicy(true)

            if (!FireManager.isLoggedIn())
                startLoginActivity()
            else
                startNextActivity()
        }

        val mDialog = dialog.show()
        mDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->

            mDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = isChecked

        }

    }


    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            PermissionsUtil.permissions(),
            PERMISSION_REQUEST_CODE
        )
    }

    private fun startPrivacyPolicyActivity() {
        val intent = Intent(this, AgreePrivacyPolicyActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }


    private fun getHtml4(textView: TextView) {

        val html = resources.getString(R.string.privacy_policy_html)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            textView.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT))
        } else {
            textView.setText(Html.fromHtml(html))
        }

    }

    private fun startLoginActivity() {
        val intent = Intent(this, AuthenticationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun startNextActivity() {
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (PermissionsUtil.permissionsGranted(grantResults)) {
            if (!FireManager.isLoggedIn())
                startLoginActivity()
            else
                startNextActivity()
        } else
            showAlertDialog()
    }

    private fun showAlertDialog() {

        val positiveClickListener =
            DetachableClickListener.wrap { dialogInterface, i -> requestPermissions() }

        val negativeClickListener = DetachableClickListener.wrap { dialogInterface, i -> finish() }


        val builder = AlertDialog.Builder(this)
            .setTitle(R.string.missing_permissions)
            .setMessage(R.string.you_have_to_grant_permissions)
            .setPositiveButton(R.string.ok, positiveClickListener)
            .setNegativeButton(R.string.no_close_the_app, negativeClickListener)
            .create()

        //avoid memory leaks
        positiveClickListener.clearOnDetach(builder)
        negativeClickListener.clearOnDetach(builder)
        builder.show()
    }
}
