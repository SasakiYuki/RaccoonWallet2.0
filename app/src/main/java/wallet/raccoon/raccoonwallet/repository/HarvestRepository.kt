package wallet.raccoon.raccoonwallet.repository

import wallet.raccoon.raccoonwallet.rest.service.HarvestService
import javax.inject.Inject

class HarvestRepository @Inject constructor(private val harvestService: HarvestService) {
    fun getHarvestInfo(address: String) = harvestService.getHarvestInfo(address)
}