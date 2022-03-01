package uz.uniconsoft.messanger.business.domain.util

import uz.uniconsoft.messanger.business.domain.model.Conversation
import uz.uniconsoft.messanger.business.domain.model.Message
import uz.uniconsoft.messanger.business.domain.model.Profile

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

val fakeMessages = listOf(
    Message(
        id = 1L,
        type = Message.Type.TYPE_TEXT,
        text = "*Assalomu alaykum!*",
        conversationId = 1L
    ),
    Message(
        id = 2L,
        type = Message.Type.TYPE_TEXT,
        text = "*Vaalaykum assalom!*",
        conversationId = 1L
    ),
)