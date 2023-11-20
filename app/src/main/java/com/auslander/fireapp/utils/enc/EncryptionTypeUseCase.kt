package com.auslander.fireapp.utils.enc

import com.auslander.fireapp.R
import com.auslander.fireapp.model.constants.EncryptionType
import com.auslander.fireapp.model.realms.Message
import com.auslander.fireapp.utils.MyApp.Companion.context

object EncryptionTypeUseCase {
     fun getEncryptionType(message: Message): String? {
        val encryptionTypeSetting =
            context().getString(R.string.encryption_type)
        return if (message.isGroup && !encryptionTypeSetting.equals(
                EncryptionType.NONE,
                ignoreCase = true
            )
        ) {
            EncryptionType.AES
        } else encryptionTypeSetting
    }
}