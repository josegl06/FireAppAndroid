package com.auslander.fireapp.adapters.messaging.holders

import android.content.Context
import android.view.View
import com.aghajari.emojiview.view.AXEmojiTextView
import com.auslander.fireapp.R
import com.auslander.fireapp.adapters.messaging.holders.base.BaseSentHolder
import com.auslander.fireapp.model.realms.Message
import com.auslander.fireapp.model.realms.User


// sent message with type text
class SentTextHolder(context: Context, itemView: View) : BaseSentHolder(context, itemView) {
    private var tvMessageContent: AXEmojiTextView = itemView.findViewById(R.id.tv_message_content)

    override fun bind(message: Message, user: User) {
        super.bind(message, user)
        tvMessageContent.text = message.content
    }

}

