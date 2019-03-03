package wallet.raccoon.raccoonwallet.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wallet constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val salt: ByteArray,
    val name: String,
    val publicKey: String = "",
    val encryptedSecretKey: ByteArray,
    val address: String,
    val isMultisig: Boolean = false
)