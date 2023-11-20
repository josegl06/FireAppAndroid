package com.auslander.fireapp.utils.enc

import com.auslander.fireapp.R
import com.auslander.fireapp.model.constants.EncryptionType
import com.auslander.fireapp.utils.MyApp
import com.auslander.fireapp.utils.enc.aes.AESCrypto
import com.auslander.fireapp.utils.enc.ethree.EthreeHelper
import kotlinx.coroutines.CoroutineScope

class EncryptionHelper {

    private val aesCrypto: AESCrypto by lazy {
        AESCrypto()
    }

    suspend fun encrypt(
        scope: CoroutineScope,
        singleUidOrMultiple: SingleUidOrMultiple,
        message: String,
        encryptionType: String,
    ): String {
        return when {
            encryptionType.equals(
                EncryptionType.AES,
                ignoreCase = true
            ) -> aesCrypto.encryptPlainTextWithRandomIV(message)
            encryptionType.equals(EncryptionType.E2E, ignoreCase = true) -> {
                if (singleUidOrMultiple.uids != null) {
                    EthreeHelper.encryptMessage(scope, singleUidOrMultiple.uids!!, message)
                } else {
                    EthreeHelper.encryptMessage(scope, singleUidOrMultiple.uid!!, message)
                }
            }
            else -> message
        }
    }


}