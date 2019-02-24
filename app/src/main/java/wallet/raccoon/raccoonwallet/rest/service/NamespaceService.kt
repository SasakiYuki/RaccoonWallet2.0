package wallet.raccoon.raccoonwallet.rest.service

import com.ryuta46.nemkotlin.model.MosaicDefinitionMetaDataPair
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NamespaceService {
  @GET("/namespace/mosaic/definition/page")
  fun namespaceMosaicDefinitionPage(
    @Query(
        "namespace"
    ) namespace: String
  ): Deferred<List<MosaicDefinitionMetaDataPair>>
}