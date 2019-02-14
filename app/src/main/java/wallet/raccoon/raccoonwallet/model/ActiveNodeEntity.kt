package wallet.raccoon.raccoonwallet.model

data class ActiveNodeEntity(
    val http: List<String>,
    val https: List<String>,
    val last_update: String
)