package wallet.raccoon.raccoonwallet.rest.service

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import com.ryuta46.nemkotlin.model.Mosaic
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfoEntity
import wallet.raccoon.raccoonwallet.model.rest.MosaicEntity
import wallet.raccoon.raccoonwallet.model.rest.TransactionEntity

interface AccountService {

  @GET("/account/transfers/all")
  fun accountTransfersAll(
    @Query(
        "address"
    ) address: String
  ): Deferred<TransactionEntity>

  @GET("/account/get")
  fun accountGet(@Query("address") address: String): Deferred<AccountMetaDataPair>

  @GET("/account/harvests")
  fun getHarvestInfo(@Query("address") address: String): Deferred<HarvestInfoEntity>

  @GET("/account/mosaic/owned")
  fun accountMosaicOwned(@Query("address") address: String): Deferred<MosaicEntity>
}