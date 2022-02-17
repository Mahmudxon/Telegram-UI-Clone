package uz.uniconsoft.messanger.business.domain.model

import uz.uniconsoft.messanger.business.domain.model.Message.Status.STATUS_WAITING
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_TEXT

class Message(
    var id: Long = 0L,
    var type: Long = TYPE_TEXT,
    var text: String = "",
    var status: Long = STATUS_WAITING,
    var forwardId: Long = 0L,
    var replyId: Long = 0L,
) {
    object Type {
        const val TYPE_TEXT = 1L
        const val TYPE_FILE = 2L
        const val TYPE_PHOTO = 3L
        const val TYPE_VIDEO = 4L
        const val TYPE_VOICE = 5L
        const val TYPE_MUSIC = 6L
        const val TYPE_CONTACT = 7L
        const val TYPE_LOCATION = 8L
    }

    object Status {
        const val STATUS_WAITING = 0L
        const val STATUS_SENT = 1L
        const val STATUS_SEEN = 2L
    }
}