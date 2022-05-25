package uz.mahmudxon.messanger.business.domain.model

sealed class Attachment {
    val id: Long = 0L
    var state: AttachmentState = AttachmentState.NotDownloaded

    data class File(
        var name: String,
        var location: String,
        var size: Long,
        var extension: String
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

    sealed class AttachmentState {
        object NotDownloaded : AttachmentState()
        data class Downloading(
            val currentBytes: Long,
            val totalBytes: Long
        ) : AttachmentState()

        object Downloaded : AttachmentState()
    }
}