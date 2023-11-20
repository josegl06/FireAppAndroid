package com.auslander.fireapp.adapters.messaging

import com.auslander.fireapp.model.realms.RealmContact

interface ContactHolderInteraction {
    fun onMessageClick(contact:RealmContact)
    fun onAddContactClick(contact:RealmContact)
}