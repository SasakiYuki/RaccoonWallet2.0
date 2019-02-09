package wallet.raccoon.raccoonwallet.repository

import wallet.raccoon.raccoonwallet.rest.AccountService
import javax.inject.Inject

class AccountRepository @Inject constructor(private val accountService: AccountService) {
  fun getAccountInfo(address: String) = accountService.accountGet(address)
}