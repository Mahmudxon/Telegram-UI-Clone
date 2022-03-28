package uz.uniconsoft.messanger.business.domain.model

import uz.uniconsoft.messanger.presentation.ui.chat.AttachmentState

sealed class Attachment {
    val id: Long = 0L
    var state: AttachmentState = AttachmentState.NotDownloaded

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

    data class Video(
        var location: String,
        var size: Long,
        var length: Long,
        var thumbnail: String,
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