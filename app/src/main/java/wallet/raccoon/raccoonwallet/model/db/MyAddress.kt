package wallet.raccoon.raccoonwallet.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyAddress constructor(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val walletInfoId: Long
)
