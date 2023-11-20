package com.auslander.fireapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.auslander.fireapp.R
import com.auslander.fireapp.activities.main.MainActivity
import com.auslander.fireapp.databinding.ActivityUpdateBinding
import com.auslander.fireapp.events.ExitUpdateActivityEvent
import com.auslander.fireapp.utils.IntentUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {
            try {
                startActivity(IntentUtils.getOpenWebsiteIntent(getString(R.string.update_app_link)))
            } catch (e: Exception) {
                Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
            }

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun exitActivity( data: ExitUpdateActivityEvent){
        startActivity(Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
        finish()
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }
}