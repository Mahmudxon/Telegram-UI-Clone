package uz.uniconsoft.messanger.business.domain.util

import uz.uniconsoft.messanger.business.domain.model.Attachment
import uz.uniconsoft.messanger.business.domain.model.Conversation
import uz.uniconsoft.messanger.business.domain.model.Message
import uz.uniconsoft.messanger.business.domain.model.Profile
import uz.uniconsoft.messanger.presentation.ui.chat.AttachmentState

val fakeProfiles = listOf(
    Profile(
        id = 1L,
        firstname = "Jerome",
        lastname = "Nelson",
        photo = "https://randomuser.me/api/portraits/men/81.jpg",
        phone = "(264)-902-9972"
    ),
    Profile(
        id = 2L,
        firstname = "Riley",
        lastname = "Gonzales",
        photo = "https://randomuser.me/api/portraits/women/19.jpg",
        phone = "(835)-601-1702"
    ),
    Profile(
        id = 3L,
        firstname = "Jeffrey",
        lastname = "Watts",
        photo = "https://randomuser.me/api/portraits/men/80.jpg",
        phone = "(227)-186-7979"
    ),
    Profile(
        id = 4L,
        firstname = "Wanda",
        lastname = "Robertson",
        photo = "https://randomuser.me/api/portraits/women/22.jpg",
        phone = "(530)-544-2331"
    ),
    Profile(
        id = 5L,
        firstname = "Terrence",
        lastname = "Mason",
        photo = "https://randomuser.me/api/portraits/men/33.jpg",
        phone = "(614)-869-7105"
    ),
    Profile(
        id = 6L,
        firstname = "Wanda",
        lastname = "Lawson",
        photo = "https://randomuser.me/api/portraits/women/29.jpg",
        phone = "(559)-787-8049"
    ),
    Profile(
        id = 7L,
        firstname = "Luke",
        lastname = "Hunt",
        photo = "https://randomuser.me/api/portraits/men/79.jpg",
        phone = "(644)-234-2974"
    ),
    Profile(
        id = 8L,
        firstname = "Rosa",
        lastname = "Stevens",
        photo = "https://randomuser.me/api/portraits/women/73.jpg",
        phone = "(686)-995-0452"
    ),
    Profile(
        id = 9L,
        firstname = "Russell",
        lastname = "Henry",
        photo = "https://randomuser.me/api/portraits/men/15.jpg",
        phone = "(282)-494-9447"
    ),
    Profile(
        id = 10L,
        firstname = "Katie",
        lastname = "Newman",
        photo = "https://randomuser.me/api/portraits/women/25.jpg",
        phone = "(408)-918-1917"
    )
)

val fakeConversation = listOf(
    Conversation(
        id = 1L,
        type = Conversation.Type.TYPE_PROFILE
    ),

    Conversation(
        id = 2L,
        type = Conversation.Type.TYPE_GROUP,
        ownerId = 1L,
        title = "Unicon-soft Chat"
    ),

    Conversation(
        id = 3L,
        type = Conversation.Type.TYPE_CHANNEL,
        ownerId = 1L,
        title = "Unicon-soft Channel"
    )
)

fun getFakeMessages(): List<Message> {
    val photos =
        mutableListOf(
            Attachment.Photo(
                location = "",
                thumbnail = "https://images.unsplash.com/photo-1510272940641-589fcd43e485?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80",
                size = 1024,
                blur = "",
                ratio = ""
            ),
            Attachment.Photo(
                location = "",
                thumbnail = "https://images.unsplash.com/photo-1648258457541-ea06dd552055?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80",
                size = 1024,
                blur = "",
                ratio = ""
            ),
            Attachment.Photo(
                location = "",
                thumbnail = "https://images.unsplash.com/photo-1599420186946-7b6fb4e297f0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80",
                size = 1024,
                blur = "",
                ratio = ""
            ),
            Attachment.Photo(
                location = "",
                thumbnail = "https://images.unsplash.com/photo-1648228446196-879d8078d9dc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2340&q=80",
                size = 1024,
                blur = "",
                ratio = ""
            ),
            Attachment.Photo(
                location = "",
                thumbnail = "https://images.unsplash.com/photo-1648277511183-c2caf34f428b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1364&q=80",
                size = 1024,
                blur = "",
                ratio = ""
            ),
        )


    photos[0].state = AttachmentState.Downloading(128, 1024)
    photos[2].state = AttachmentState.Downloaded

    val message1 = Message()
    message1.type = Message.Type.TYPE_TEXT
    message1.attachment = photos
    message1.text = "Normal `code`  *bold*   https://google.com  @username"
    message1.status = Message.Status.STATUS_SEEN

    val message2 = Message()
    message2.type = Message.Type.TYPE_PHOTO
    message2.attachment = photos
    message2.text = "Normal `code`  *bold*   https://google.com  @username"
    message2.status = Message.Status.STATUS_SENT

    val message3 = Message()
    message3.type = Message.Type.TYPE_TEXT
    message3.attachment = photos
    message3.text = "Normal `code`  *bold*   https://google.com  @username"
    message3.status = Message.Status.STATUS_WAITING

    return listOf(message1, message2, message3)
}