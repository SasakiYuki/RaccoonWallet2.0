package wallet.raccoon.raccoonwallet.rest.service

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import wallet.raccoon.raccoonwallet.model.rest.MosaicMetaDataEntity

interface NamespaceService {
  @GET("/namespace/mosaic/definition/page")
  fun namespaceMosaicDefinitionPage(
    @Query(
        "namespace"
    ) namespace: String
  ): Deferred<MosaicMetaDataEntity>
}