package wallet.raccoon.raccoonwallet.rest.service

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import wallet.raccoon.raccoonwallet.model.rest.ActiveNodeEntity

interface XembookService {

    @GET("data/v2/node.json")
    fun getActiveNode(): Deferred<ActiveNodeEntity>
}