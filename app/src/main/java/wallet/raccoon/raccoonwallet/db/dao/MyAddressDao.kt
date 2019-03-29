package wallet.raccoon.raccoonwallet.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import wallet.raccoon.raccoonwallet.model.db.MyAddress

@Dao
interface MyAddressDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(myAddress: MyAddress)

  @Query("SELECT * FROM MyAddress")
  fun findAll(): List<MyAddress>

  @Update
  fun update(myAddress: MyAddress)

  @Delete
  fun delete(myAddress: MyAddress)
}
