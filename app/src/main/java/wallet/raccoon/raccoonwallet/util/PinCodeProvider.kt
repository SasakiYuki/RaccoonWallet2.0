package wallet.raccoon.raccoonwallet.util

object PinCodeProvider {
    private val defaultValue = byteArrayOf(50, 57, 54, 56, 53, 49)
    private var pinCode: ByteArray? = null

    fun setPinCode(pinCode: String) {
        this.pinCode = pinCode.toByteArray(Charsets.UTF_8)
    }

    fun getPinCode() =
        pinCode?.let {
            return it
        } ?: run {
            defaultValue
        }

    fun clearCache() {
        pinCode = null
    }
}
