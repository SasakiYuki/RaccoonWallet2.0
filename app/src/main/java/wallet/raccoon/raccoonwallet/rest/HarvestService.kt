package wallet.raccoon.raccoonwallet.rest

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import wallet.raccoon.raccoonwallet.model.rest.HarvestInfos

interface HarvestService {

    @GET("/account/harvests")
    fun getHarvestInfo(@Query("address") address: String): Deferred<HarvestInfos>
}