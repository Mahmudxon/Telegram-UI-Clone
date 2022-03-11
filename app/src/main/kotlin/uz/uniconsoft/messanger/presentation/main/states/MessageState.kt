package uz.uniconsoft.messanger.presentation.main.states

sealed class AttachmentState {
    data class BlurPreview(val preview: String) : AttachmentState()
    data class Downloading(
        val preview: String,
        val currentBytes: Long,
        val totalBytes: Long
    ) : AttachmentState()

    data class Downloaded(val localFileName: String) : AttachmentState()
}