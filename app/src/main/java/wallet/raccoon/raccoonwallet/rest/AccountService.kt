package wallet.raccoon.raccoonwallet.rest

import com.ryuta46.nemkotlin.model.AccountMetaDataPair
import com.ryuta46.nemkotlin.model.TransactionMetaDataPair
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfos

interface AccountService {

  @GET("/account/transfers/all")
  fun accountTransfersAll(
    @Query(
        "address"
    ) address: String
  ): Deferred<List<TransactionMetaDataPair>>

  @GET("/account/get")
  fun accountGet(@Query("address") address: String): Deferred<AccountMetaDataPair>

  @GET("/account/harvests")
  fun getHarvestInfo(@Query("address") address: String): Deferred<HarvestInfos>
}