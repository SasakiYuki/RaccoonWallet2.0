package wallet.raccoon.raccoonwallet.rest

import com.ryuta46.nemkotlin.model.HarvestInfo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Objects

interface HarvestService {

    @GET("/account/harvests")
    fun getHarvestInfo(@Query("address") address: String): Deferred<Objects>
}