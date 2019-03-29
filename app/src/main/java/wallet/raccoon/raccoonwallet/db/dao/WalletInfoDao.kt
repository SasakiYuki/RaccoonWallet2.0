package wallet.raccoon.raccoonwallet.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import wallet.raccoon.raccoonwallet.model.db.WalletInfo

@Dao
interface WalletInfoDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(walletInfo: WalletInfo): Long

  @Query("SELECT * FROM WalletInfo")
  fun findAll(): List<WalletInfo>

  @Query("SELECT * FROM WalletInfo WHERE id = :id")
  fun select(id: Long): WalletInfo

  @Update(onConflict = OnConflictStrategy.REPLACE)
  fun update(walletInfo: WalletInfo)

  @Delete
  fun delete(walletInfo: WalletInfo)
}
