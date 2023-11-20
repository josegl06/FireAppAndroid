package com.auslander.fireapp.adapters.messaging.holders

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.auslander.fireapp.R
import com.auslander.fireapp.activities.ContactDetailsActivity
import com.auslander.fireapp.adapters.messaging.ContactHolderBase
import com.auslander.fireapp.adapters.messaging.ContactHolderInteraction
import com.auslander.fireapp.adapters.messaging.holders.base.BaseSentHolder
import com.auslander.fireapp.model.realms.Message
import com.auslander.fireapp.model.realms.User
import com.auslander.fireapp.utils.IntentUtils

class SentContactHolder(context: Context, itemView: View) : BaseSentHolder(context,itemView),ContactHolderBase {

    private val tvContactName: TextView = itemView.findViewById(R.id.tv_contact_name)
    private val btnMessageContact: Button = itemView.findViewById(R.id.btn_message_contact)

    override var contactHolderInteraction: ContactHolderInteraction? = null

    override fun bind(message: Message,user: User) {
        super.bind(message,user)
        //set contact name
        tvContactName.text = message.content


        //send a message to this contact if installed this app
        btnMessageContact.setOnClickListener {
            contactHolderInteraction?.onMessageClick(message.contact)
        }

    }



}

