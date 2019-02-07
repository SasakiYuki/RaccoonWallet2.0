package wallet.raccoon.raccoonwallet.rest

import com.ryuta46.nemkotlin.client.RxNemApiClient

abstract class BaseClientService {
    fun getClient() = RxNemApiClient(ApiManager.getBaseUrl())
}