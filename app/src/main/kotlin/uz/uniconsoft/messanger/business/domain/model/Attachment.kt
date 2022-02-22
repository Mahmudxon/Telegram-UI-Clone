package uz.uniconsoft.messanger.business.domain.model

sealed class Attachment {

    data class File(
        var name: String,
        var location: String,
        var size: Long
    ) : Attachment()

    data class Photo(
        var location: String,
        var thumbnail: String,
        var size: Long,
        var blur: String,
        var ratio: String // w:h
    ) : Attachment()

    data class Voice(
        var location: String,
        var size: Long,
        var length: Long
    ) : Attachment()

    data class Music(
        var name: String,
        var artist: String? = null,
        var size: Long,
        var length: Long
    ) : Attachment()

    data class Contact(
        var firstName: String,
        var lastName: String,
        var phone: String
    ) : Attachment()

    data class Location(
        var name: String?,
        var latitude: Double,
        var longitude: Double
    ) : Attachment()

}