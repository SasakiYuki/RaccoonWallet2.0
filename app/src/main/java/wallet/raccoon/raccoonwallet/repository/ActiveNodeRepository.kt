package wallet.raccoon.raccoonwallet.repository

import wallet.raccoon.raccoonwallet.rest.service.XembookService
import javax.inject.Inject

class ActiveNodeRepository @Inject constructor(private val xembookService: XembookService) {
    fun getActiveNodeList() = xembookService.getActiveNode()
}