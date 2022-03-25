package uz.uniconsoft.messanger.business.domain.util

import androidx.compose.runtime.compositionLocalOf
import uz.uniconsoft.messanger.business.domain.model.Attachment

interface IFileManager {
    fun getAttachmentsDir(attachment: Attachment): String
    fun getAttachmentDownloadedPath(attachment: Attachment) : String
}

val LocalFileManager = compositionLocalOf<IFileManager> { error("No FileManager") }