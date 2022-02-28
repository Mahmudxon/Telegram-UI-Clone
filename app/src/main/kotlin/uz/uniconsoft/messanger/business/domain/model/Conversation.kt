package uz.uniconsoft.messanger.business.domain.model

import uz.uniconsoft.messanger.business.domain.model.Conversation.Notification.NOTIFICATIONS_DEFAULT
import uz.uniconsoft.messanger.business.domain.model.Conversation.Notification.NOTIFICATIONS_ON
import uz.uniconsoft.messanger.business.domain.model.Conversation.Type.TYPE_GROUP
import uz.uniconsoft.messanger.business.domain.model.Conversation.Type.TYPE_PROFILE

data class Conversation(
    var id: Long = 0L, // chat id or profile id
    var type: Long = TYPE_GROUP, // chat type
    var ownerId: Long = 0L, // Account.id // for group or channels only
    var title: String = "",
    var description: String = "",
    var photo: String = "",
    var link: String = "",

    // access controls
    var canRemoveMember: Boolean = false,
    var canAddMember: Boolean = false,
    var canChangePhoto: Boolean = false,
    var canChangeTitle: Boolean = false,


    var memberCount: Long = 0L,
    var degree: Long = 0L,
    var createdDate: Long = 0L,
    var flags: Long = 0L,

    // dialog
    var lastMessageId: Long = 0L,
    var lastReadMessageIdByOthers: Long = 0L,
    var lastReadMessageIdBySelf: Long = 0L,
    var unreadCount: Long = 0L,
    var notifySetting: Long = 0L,
) {

    fun notify() = notifySetting == NOTIFICATIONS_DEFAULT || notifySetting == NOTIFICATIONS_ON

    fun withProfile(profile: Profile): Conversation {
        this.id = profile.id
        this.type = TYPE_PROFILE
        this.title = profile.fullname()
        this.photo = profile.photo
        this.degree = profile.degree
        return this
    }

    object Type {
        const val TYPE_ANY = -1L
        const val TYPE_PROFILE = 0L
        const val TYPE_GROUP = 1L
        const val TYPE_CHANNEL = 2L
        const val TYPE_TEMPORARY_CHANNEL = 3L
    }

    object Notification {
        const val NOTIFICATIONS_ON = 1L
        const val NOTIFICATIONS_DEFAULT = 0L
        const val NOTIFICATIONS_OFF = -1L
    }
}