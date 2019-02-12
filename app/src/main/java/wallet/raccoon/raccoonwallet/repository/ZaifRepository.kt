package wallet.raccoon.raccoonwallet.repository

import wallet.raccoon.raccoonwallet.rest.service.ZaifService
import javax.inject.Inject

class ZaifRepository @Inject constructor(private val zaifService: ZaifService) {
  fun getNemPrice() = zaifService.getNemPrice()
}