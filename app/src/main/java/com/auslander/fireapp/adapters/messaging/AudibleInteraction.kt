package com.auslander.fireapp.adapters.messaging

import com.auslander.fireapp.model.realms.Message

interface AudibleInteraction {
    fun onSeek(message:Message,progress:Int,max:Int)
}