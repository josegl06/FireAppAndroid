package com.auslander.fireapp.adapters.messaging.holders

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.auslander.fireapp.R
import com.auslander.fireapp.adapters.messaging.holders.base.BaseSentHolder
import com.auslander.fireapp.common.extensions.setHidden
import com.auslander.fireapp.model.constants.DownloadUploadStat
import com.auslander.fireapp.model.realms.Message
import com.auslander.fireapp.model.realms.User
import com.auslander.fireapp.utils.Util


class SentFileHolder(context: Context, itemView: View) : BaseSentHolder(context,itemView) {

    private val tvFileSize: TextView = itemView.findViewById(R.id.tv_file_size)
    private val tvFileName: TextView = itemView.findViewById(R.id.tv_file_name)
    private val tvFileExtension: TextView = itemView.findViewById(R.id.tv_file_extension)


    private val fileIcon: ImageView = itemView.findViewById(R.id.file_icon)
    override fun bind(message: Message,user: User) {
        super.bind(message,user)
        val fileExtension = Util.getFileExtensionFromPath(message.metadata).toUpperCase()
        tvFileExtension.text = fileExtension
        //set file name
        tvFileName.text = message.metadata

        //file size
        tvFileSize.text = message.fileSize

        fileIcon.setHidden(message.downloadUploadStat != DownloadUploadStat.SUCCESS, true)

    }


}
