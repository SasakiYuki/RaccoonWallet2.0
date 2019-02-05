package wallet.raccoon.raccoonwallet.usecase

import wallet.raccoon.raccoonwallet.repository.HarvestRepository
import javax.inject.Inject

class HomeFragmentUseCase @Inject constructor(private val harvestRepository: HarvestRepository) {
    fun getHarvestInfo(address: String) = harvestRepository.getHarvestInfo(address)
}