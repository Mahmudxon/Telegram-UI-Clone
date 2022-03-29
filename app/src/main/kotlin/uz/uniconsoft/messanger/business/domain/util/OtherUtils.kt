package uz.uniconsoft.messanger.business.domain.util

val Any.TAG: String
    get() = "TTT@${this.javaClass.canonicalName?.split('.')?.last()}"

fun Long.toStringAsFileSize(): String {
    var temp = this.toFloat()
    if (temp < 1024)
        return "${temp.toInt()} B"

    temp /= 1024

    if (temp < 1024)
        return "%.2f KB".format(temp)
    temp /= 1024

    if (temp < 1024)
        return "%.2f MB".format(temp)
    temp /= 1024
    return "%.2f GB".format(temp)
}