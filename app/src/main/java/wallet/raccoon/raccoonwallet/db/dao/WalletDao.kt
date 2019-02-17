package wallet.raccoon.raccoonwallet.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import wallet.raccoon.raccoonwallet.model.db.Wallet

@Dao
interface WalletDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(wallet: Wallet)

    @Query("SELECT * FROM Wallet WHERE id = :walletId")
    suspend fun findById(walletId: Long): Wallet

    @Query("SELECT * FROM Wallet ORDER BY id ASC")
    suspend fun findAll(): List<Wallet>
}