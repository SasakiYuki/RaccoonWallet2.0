package wallet.raccoon.raccoonwallet.repository

import wallet.raccoon.raccoonwallet.rest.AccountService
import javax.inject.Inject

class HarvestRepository @Inject constructor(private val acc: AccountService) {
  fun getHarvestInfo(address: String) = acc.getHarvestInfo(address)
}