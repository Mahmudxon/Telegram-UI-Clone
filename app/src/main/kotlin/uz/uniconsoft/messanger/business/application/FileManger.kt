package uz.uniconsoft.messanger.business.application

import android.content.Context
import uz.uniconsoft.messanger.business.domain.model.Attachment
import uz.uniconsoft.messanger.business.domain.util.IFileManager

class FileManger(context: Context) : IFileManager {

    private val baseDir by lazy { context.filesDir.absolutePath }

    private val filesDir by lazy { "$baseDir/files/" }
    private val photosDir by lazy { "$baseDir/photos/" }
    private val videosDir by lazy { "$baseDir/videos/" }
    private val audiosDir by lazy { "$baseDir/audio/" }


    override fun getAttachmentsDir(attachment: Attachment): String {
        return when (attachment) {
            is Attachment.Photo -> photosDir
            is Attachment.Video -> videosDir
            is Attachment.Music,
            is Attachment.Voice -> audiosDir
            else -> filesDir
        }
    }

    override fun getAttachmentDownloadedPath(attachment: Attachment): String =
        "${getAttachmentsDir(attachment)}/${attachment.id}"
}