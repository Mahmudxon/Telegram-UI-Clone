package uz.uniconsoft.messanger.business.domain.model

sealed class Attachment {

    data class File(
        var name: String,
        var location: String,
        var size: Long
    ) : Attachment()

    /**
     *  attachmentLocation = attachment?.location ?: ""
    attachmentThumbnail = attachment?.thumbnail ?: ""
    attachmentSize = attachment?.size?.toLong() ?: 0L
    attachmentWHRatio = attachment?.whRatio?.toString() ?: ""
    attachmentBlur = attachment?.blur?.toBase64() ?: ""
    attachmentWHRatio = attachment?.whRatio?.toString() ?: ""
     * */
    data class Photo(
        var location: String,
        var thumbnail: String,
        var size: Long,
        var blur: String,
        var ratio: String // w:h
    ) : Attachment()
}