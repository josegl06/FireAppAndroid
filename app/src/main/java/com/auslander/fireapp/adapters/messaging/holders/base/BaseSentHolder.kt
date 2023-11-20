package com.auslander.fireapp.adapters.messaging.holders.base

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.auslander.fireapp.R
import com.auslander.fireapp.model.realms.Message
import com.auslander.fireapp.model.realms.QuotedMessage
import com.auslander.fireapp.model.realms.User
import com.auslander.fireapp.utils.AdapterHelper
import com.auslander.fireapp.utils.MessageTypeHelper

open class BaseSentHolder(context: Context, itemView: View) : BaseHolder(context,itemView) {

    var messageStatImg:ImageView? = itemView.findViewById(R.id.message_stat_img)


    override fun bind(message: Message, user: User) {
        super.bind(message, user)


        //imgStat (received or read)
        messageStatImg?.setImageResource(AdapterHelper.getMessageStatDrawable(message.messageStat))


    }




}

