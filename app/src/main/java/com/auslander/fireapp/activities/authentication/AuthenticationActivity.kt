package com.auslander.fireapp.activities.authentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.auslander.fireapp.R
import com.auslander.fireapp.activities.SplashActivity
import com.auslander.fireapp.databinding.ActivityAuthenticationBinding
import com.auslander.fireapp.utils.network.AuthManager
import com.auslander.fireapp.activities.authentication.AuthenticationViewModel


class AuthenticationActivity : AppCompatActivity(), AuthCallbacks {

    private val authManager = AuthManager()

    private val viewModel: AuthenticationViewModel by viewModels()

    private lateinit var navigation: NavController

    lateinit var navHostFragment: NavHostFragment
    private lateinit var binding:ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigation = Navigation.findNavController(this, R.id.nav_host_fragment)
        navigation.setGraph(R.navigation.nav_signup)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment


        subscribeObservers()



    }

    private fun subscribeObservers() {
        viewModel.goToEnterVerifyPhoneFragment.observe(this) {
            navigation.navigate(R.id.action_enterPhoneNumberFragment_to_verifyPhoneFragment, it)
        }

        viewModel.showLoading.observe(this) {
            setLoading(it)
        }

        viewModel.goToAuthActivity.observe(this) {
            startActivity(Intent(this, AuthenticationActivity::class.java))
        }

        viewModel.showMessage.observe(this) {
            AlertDialog.Builder(this).apply {
                setMessage(it)
                setPositiveButton(R.string.ok, null)
                show()
            }
        }

        viewModel.verify.observe(this) {
            val (number, callbacks) = it
            authManager.verify(number, this, callbacks)
        }
    }

    override fun verifyPhoneNumber(phoneNumber: String, countryIso: String) {
        viewModel.verifyPhoneNumber(phoneNumber, countryIso)
    }


    override fun verifyCode(code: String) {
        viewModel.verifyCode(code)


    }

    override fun cancelVerificationRequest() {
        viewModel.cancelVerification()
    }


    private fun setLoading(setLoading: Boolean) {
        binding.progressbar.isVisible = setLoading

        navHostFragment.childFragmentManager.fragments.getOrNull(0)?.let { fragment ->
            if (fragment is BaseAuthFragment) {
                if (setLoading)
                    fragment.disableViews()
                else
                    fragment.enableViews()

            }
        }


    }

    override fun onStart() {
        super.onStart()
        if (viewModel.isLoggedIn()) {
            startSplashActivity()
        }

    }

    private fun startSplashActivity() {
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finish()
    }


}