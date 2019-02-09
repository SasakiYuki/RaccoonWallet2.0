package wallet.raccoon.raccoonwallet.model.local

import com.ryuta46.nemkotlin.model.HarvestInfo
import wallet.raccoon.raccoonwallet.extentions.convertNEMFromMicroToDouble
import wallet.raccoon.raccoonwallet.extentions.getNemStartDateTimeLong
import java.text.SimpleDateFormat
import java.util.Date

data class HarvestItem(
  val timeString: String,
  val totalFee: String
) {
  companion object {
    fun convert(harvestInfo: HarvestInfo) = HarvestItem(
        convertDate(harvestInfo.timeStamp),
        convertFee(harvestInfo.totalFee)
    )

    private fun convertDate(timeStamp: Int): String {
      val nemStartTimeLong = getNemStartDateTimeLong()
      val sdf = SimpleDateFormat("MM/dd yyyy")
      val date = Date(timeStamp.toLong() * 1000 + nemStartTimeLong)
      return sdf.format(date)
    }

    private fun convertFee(totalFee: Long) = totalFee.convertNEMFromMicroToDouble().toString()
  }
}