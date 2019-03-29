package wallet.raccoon.raccoonwallet.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import wallet.raccoon.raccoonwallet.extentions.toDisplayAddress
import java.io.Serializable

@Entity
data class WalletInfo
constructor(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0,
  var walletName: String = "",
  var walletAddress: String = "",
  var isMaster: Boolean = false
) : Serializable {
  fun displayWalletAddress() = walletAddress.toDisplayAddress()
}
