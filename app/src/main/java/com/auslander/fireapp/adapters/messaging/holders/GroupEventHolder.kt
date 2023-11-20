package com.auslander.fireapp.adapters.messaging.holders

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.auslander.fireapp.R
import com.auslander.fireapp.adapters.messaging.holders.base.BaseHolder
import com.auslander.fireapp.model.realms.GroupEvent
import com.auslander.fireapp.model.realms.Message
import com.auslander.fireapp.model.realms.User

 class GroupEventHolder(context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvGroupEvent: TextView = itemView.findViewById(R.id.tv_group_event)

     fun bind(message: Message,user: User){
         tvGroupEvent.text = GroupEvent.extractString(message.content, user.group.users)
     }


}