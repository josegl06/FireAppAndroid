package com.auslander.fireapp.adapters.messaging.holders

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.auslander.fireapp.R
import com.auslander.fireapp.adapters.messaging.holders.base.BaseSentHolder
import com.auslander.fireapp.model.constants.DownloadUploadStat
import com.auslander.fireapp.model.realms.Message
import com.auslander.fireapp.model.realms.User
import com.auslander.fireapp.utils.FileUtils

import java.io.File

// sent message with type image
class SentImageHolder(context: Context, itemView: View) : BaseSentHolder(context, itemView) {

    var imgMsg: ImageView = itemView.findViewById(R.id.img_msg)

    override fun bind(message: Message, user: User) {
        super.bind(message, user)
        //if the image is not downloaded show thumb img
        if (message.localPath == null) {
            try {
                Glide.with(context).load(message.thumb).into(imgMsg)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (!File(message.localPath).exists()) {
            // if image deleted from device then show the blurred thumbnail
            try {
                Glide.with(context).load(message.thumb).into(imgMsg)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            //these try catch exceptions because glide does not support set tag to an image view
            try {
                Glide.with(context).load(Uri.fromFile(File(message.localPath))).into(imgMsg)
                ViewCompat.setTransitionName(imgMsg, message.messageId)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }


}