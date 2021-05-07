package viewmodel

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

fun Int.toDateBR(): String {
    val formatter = SimpleDateFormat("dd/MM H:mm", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this.toLong()
    return formatter.format(calendar.time)
}


fun Double.formatarMoeda(): String {
    val otherSymbols = DecimalFormatSymbols(Locale.getDefault())
    otherSymbols.decimalSeparator = ','
    otherSymbols.groupingSeparator = '.'
    return DecimalFormat("R$ ###,##0.00", otherSymbols).format(this)

}