package wallet.raccoon.raccoonwallet.repository

import wallet.raccoon.raccoonwallet.rest.service.AccountService
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val accountService: AccountService) {
  fun getAccountTransfersAll(address: String) = accountService.accountTransfersAll(address)
}