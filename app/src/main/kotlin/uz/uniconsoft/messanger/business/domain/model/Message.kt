package uz.uniconsoft.messanger.business.domain.model

import uz.uniconsoft.messanger.business.domain.model.Message.Status.STATUS_WAITING
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_ADD_ACCOUNT
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_CREATED_CHAT
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_DELETE_ACCOUNT
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_DELETE_PHOTO
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_FILE
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_HISTORY_CLEARED
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_JOINED
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_MUSIC
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_PHOTO
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_SET_ACCESS
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_SET_PHOTO
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_SET_TITLE
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_TEXT
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_VIDEO
import uz.uniconsoft.messanger.business.domain.model.Message.Type.TYPE_VOICE

class Message(
    var id: Long = 0L,
    var type: Long = TYPE_TEXT,
    var conversationId: Long = 0L,
    var text: String = "",
    var status: Long = STATUS_WAITING,
    var forwardId: Long = 0L,
    var replyId: Long = 0L,
    var date: Long = 0L,
    var isDeleted: Boolean = false,
    var editedTime: Long = 0L,
    var attachment: List<Attachment> = listOf()
) {
    fun isEvent(): Boolean {
        return type == TYPE_CREATED_CHAT ||
                type == TYPE_SET_TITLE ||
                type == TYPE_SET_PHOTO ||
                type == TYPE_DELETE_PHOTO ||
                type == TYPE_ADD_ACCOUNT ||
                type == TYPE_DELETE_ACCOUNT ||
                type == TYPE_SET_ACCESS ||
                type == TYPE_HISTORY_CLEARED ||
                type == TYPE_JOINED
    }

    fun isFileMessage(): Boolean {
        return type == TYPE_FILE ||
                type == TYPE_PHOTO ||
                type == TYPE_VIDEO ||
                type == TYPE_VOICE ||
                type == TYPE_MUSIC
    }

    object Type {
        const val TYPE_UNSUPPORTED = 0L
        const val TYPE_TEXT = 1L
        const val TYPE_FILE = 2L
        const val TYPE_PHOTO = 3L
        const val TYPE_VIDEO = 4L
        const val TYPE_VOICE = 5L
        const val TYPE_MUSIC = 6L
        const val TYPE_CONTACT = 7L
        const val TYPE_LOCATION = 8L
        const val TYPE_CREATED_CHAT = 9L
        const val TYPE_SET_TITLE = 10L
        const val TYPE_SET_PHOTO = 11L
        const val TYPE_DELETE_PHOTO = 12L
        const val TYPE_ADD_ACCOUNT = 13L
        const val TYPE_DELETE_ACCOUNT = 14L
        const val TYPE_SET_ACCESS = 15L
        const val TYPE_HISTORY_CLEARED = 16L
        const val TYPE_CALL = 17L
        const val TYPE_JOINED = 18L
    }

    object Status {
        const val STATUS_WAITING = 0L
        const val STATUS_SENT = 1L
        const val STATUS_SEEN = 2L
        const val STATUS_ERROR = 3L
    }

    object Progress {
        const val PROGRESS_IDLE = 0L
        const val PROGRESS_LOADING = 1L
        const val PROGRESS_PLAYING = 2L
        const val PROGRESS_PAUSED = 3L
    }
}