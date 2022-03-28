package uz.uniconsoft.messanger.presentation.ui.chat

sealed class AttachmentState {
    object NotDownloaded : AttachmentState()
    data class Downloading(
        val currentBytes: Long,
        val totalBytes: Long
    ) : AttachmentState()

    object Downloaded : AttachmentState()
}