package wallet.raccoon.raccoonwallet.rest.service

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import wallet.raccoon.raccoonwallet.model.rest.ZaifNemEntity

interface ZaifService {
  /**
   * ZaifのNemの価格を取得する
   */
  @GET("api/1/last_price/xem_jpy")
  fun getNemPrice(): Deferred<ZaifNemEntity>
}