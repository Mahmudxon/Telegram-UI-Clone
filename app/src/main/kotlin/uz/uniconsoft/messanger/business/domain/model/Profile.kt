package uz.uniconsoft.messanger.business.domain.model

data class Profile(
    var id: Long = 0L,
    var type: String = OTHER,
    var firstname: String = "",
    var lastname: String = "",
    var username: String = "",
    var status: String = "",
    var phone: String = "",
    var photo: String = "",
    var lastOnlineAt: Long = 0L,
    var flags: Long = 0L,
    var degree: Long = 0L,

    var isSelected: Boolean = false,
    var isOnline: Boolean = false
) {
    companion object {
        const val SELF = "0"
        const val FRIEND = "1"
        const val FOREIGN = "2"
        const val CONTACT = "3"
        const val OTHER = "4"
        const val SERVICE = "5"
        const val REQUEST = "6"
    }

    fun fullname() = "$firstname $lastname"

    fun isBlocked() = (flags and 1L) == 1L

    fun setBlocked(b: Boolean) {
        flags = if (b) {
            flags or 0b1
        } else {
            flags and 0b0
        }
    }
}