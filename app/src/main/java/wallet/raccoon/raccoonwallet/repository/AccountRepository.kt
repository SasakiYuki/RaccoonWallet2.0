package wallet.raccoon.raccoonwallet.repository

import com.ryuta46.nemkotlin.account.AccountGenerator
import com.ryuta46.nemkotlin.enums.Version
import wallet.raccoon.raccoonwallet.rest.service.AccountService
import javax.inject.Inject

class AccountRepository @Inject constructor(private val accountService: AccountService) {
    fun getAccountInfo(address: String) = accountService.accountGet(address)
    fun getAccountMosaicOwned(address: String) = accountService.accountMosaicOwned(address)
    fun createNewAccount() = AccountGenerator.fromRandomSeed(Version.Main)
}