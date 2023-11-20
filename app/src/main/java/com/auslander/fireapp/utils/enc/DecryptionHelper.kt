package com.auslander.fireapp.utils.enc

import com.auslander.fireapp.model.constants.EncryptionType
import com.auslander.fireapp.utils.enc.aes.AESCrypto
import com.auslander.fireapp.utils.enc.ethree.EthreeHelper
import kotlinx.coroutines.CoroutineScope

class DecryptionHelper {

    private val aesCrypto: AESCrypto by lazy {
        AESCrypto()
    }


    suspend fun decrypt(
        scope: CoroutineScope,
        fromId: String,
        message: String,
        encryptionType: String
    ): String {
        return when {
            encryptionType.equals(
                EncryptionType.AES,
                ignoreCase = true
            ) -> aesCrypto.decryptCipherTextWithRandomIV(message)
            encryptionType.equals(
                EncryptionType.E2E,
                ignoreCase = true
            ) -> EthreeHelper.decryptMessage(scope, fromId, message)
            else -> message
        }
    }


}