package wallet.raccoon.raccoonwallet.model.rest

data class ActiveNodeEntity(
    val http: List<String>,
    val https: List<String>,
    val last_update: String
)