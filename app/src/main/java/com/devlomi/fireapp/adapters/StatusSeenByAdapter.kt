package com.devlomi.fireapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devlomi.fireapp.R
import com.devlomi.fireapp.databinding.RowSeenByBinding
import com.devlomi.fireapp.model.realms.StatusSeenBy
import com.devlomi.fireapp.model.realms.User
import com.devlomi.fireapp.utils.TimeHelper
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults


class StatusSeenByAdapter(private val seenByList: RealmResults<StatusSeenBy>, callback: StatusSeenByCallback)
    : RealmRecyclerViewAdapter<StatusSeenBy, StatusSeenByAdapter.SeenByHolder>(seenByList, true) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeenByHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_seen_by, parent, false)
        return SeenByHolder(view)
    }

    override fun getItemCount() = seenByList.size

    override fun onBindViewHolder(holder: SeenByHolder, position: Int) {
        val user = seenByList[position]
        if (user != null)
            holder.bind(user)
    }

    inner class SeenByHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowSeenByBinding.bind(itemView)
        fun bind(seenByUser: StatusSeenBy) {
            val user = seenByUser.user


            val seenAt = seenByUser.seenAt
            val date = TimeHelper.getTimeAgo(seenAt)
            val timestamp = when {
                //if there are users on old version the timestamp will not be shown
                seenAt == 0L -> ""
                TimeHelper.getTimeAgo(seenAt) == "" -> itemView.context.resources.getString(R.string.now)
                else -> {
                    TimeHelper.getTimeAgo(seenAt)
                }
            }

            binding.tvUsername.text = user?.userName
            Glide.with(itemView.context).load(user?.thumbImg).into(binding.userImg)
            binding.tvSeenTime.text = timestamp
        }
    }
}

interface StatusSeenByCallback {
    fun onClick(user: User, itemView: View)
}