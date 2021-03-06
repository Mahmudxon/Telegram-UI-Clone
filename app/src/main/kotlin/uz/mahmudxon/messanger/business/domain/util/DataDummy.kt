package uz.mahmudxon.messanger.business.domain.util

data class Chat(
    val id: Int,
    val name: String,
    val time: String,
    val date: String?,
    val lastMessage: String?,
    val imageUrl: String,
    val newChatSize: Int
)

data class Message(
    val message: String,
    val isPeer: Boolean
)

object DataDummy {
    val listChat = listOf(
        Chat(
            id = 0,
            name = "Kim dir",
            time = "08:46",
            date = "17 june",
            lastMessage = "Oxirgi chatdagi xabar",
            imageUrl = "https://randomuser.me/api/portraits/men/10.jpg",
            newChatSize = 121
        ),
        Chat(
            id = 0,
            name = "Kim dir",
            time = "08:46",
            date = "17 june",
            lastMessage = "Oxirgi chatdagi xabar",
            imageUrl = "https://randomuser.me/api/portraits/men/5.jpg",
            newChatSize = 121
        ),
        Chat(
            id = 0,
            name = "Kim dir",
            time = "08:46",
            date = "17 june",
            lastMessage = "Oxirgi chatdagi xabar",
            imageUrl = "https://randomuser.me/api/portraits/men/8.jpg",
            newChatSize = 121
        ),
        Chat(
            id = 0,
            name = "Kim dir",
            time = "08:46",
            date = "17 june",
            lastMessage = "Oxirgi chatdagi xabar",
            imageUrl = "https://randomuser.me/api/portraits/men/9.jpg",
            newChatSize = 121
        ),
        Chat(
            id = 0,
            name = "Kim dir",
            time = "08:46",
            date = "17 june",
            lastMessage = "Oxirgi chatdagi xabar",
            imageUrl = "https://randomuser.me/api/portraits/men/10.jpg",
            newChatSize = 121
        ),
        Chat(
            id = 0,
            name = "Kim dir",
            time = "08:46",
            date = "17 june",
            lastMessage = "Oxirgi chatdagi xabar",
            imageUrl = "https://randomuser.me/api/portraits/men/5.jpg",
            newChatSize = 121
        ),
        Chat(
            id = 0,
            name = "Kim dir",
            time = "08:46",
            date = "17 june",
            lastMessage = "Oxirgi chatdagi xabar",
            imageUrl = "https://randomuser.me/api/portraits/men/8.jpg",
            newChatSize = 121
        ),
        Chat(
            id = 0,
            name = "Kim dir",
            time = "08:46",
            date = "17 june",
            lastMessage = "Oxirgi chatdagi xabar",
            imageUrl = "https://randomuser.me/api/portraits/men/9.jpg",
            newChatSize = 121
        ),
    )

    val listMessage = listOf(
        Message("Hi Roy how are you ?", false),
        Message("Iam fine, how are you ?", true),
        Message("Iam fine too", false),
        Message("What do you do now ?", true),
        Message("Write a book, and doing my work", false),
        Message("Wow, its so good man", true),
        Message("Yeah", false),
        Message("Wow, its so good man", true),
        Message("Yeah", false),
        Message("Wow, its so good man", true),
        Message("Yeah", false),
        Message("Wow, its so good man", true),
        Message("Yeah", false),
        Message("Wow, its so good man", true),
        Message("Yeah", false),
        Message("Wow, its so good man", true),
        Message("Yeah", false),
    )
}