package wallet.raccoon.raccoonwallet.rest.service

import io.reactivex.Single
import retrofit2.http.GET
import wallet.raccoon.raccoonwallet.rest.model.ActiveNodeEntity


interface NodeExplorerApiService {

    /**
     * 利用可なNode一覧を取得します
     */
    @GET("api_openapi")
    fun getActiveNodeList() :Single<ActiveNodeEntity>
}