package uz.uniconsoft.messanger.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import uz.uniconsoft.messanger.business.domain.model.Attachment
import uz.uniconsoft.messanger.business.domain.model.Message
import uz.uniconsoft.messanger.presentation.theme.LocalTheme
import uz.uniconsoft.messanger.presentation.theme.ThemeManger
import uz.uniconsoft.messanger.presentation.ui.chat.AttachmentState
import uz.uniconsoft.messanger.presentation.ui.chat.MessageItem
import javax.inject.Inject

@AndroidEntryPoint
class PreviewActivity : AppCompatActivity() {

    @Inject
    lateinit var themeManger: ThemeManger


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val file1 = Attachment.File(
            name = "Surface 2022.03.29_15_15.jpg",
            location = "https://images.unsplash.com/photo-1640622304293-ec9c89c6bac9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80",
            size = 102478568,
            extension = "JPEG"
        )

        file1.state = AttachmentState.Downloaded
        // AttachmentState.NotDownloaded // AttachmentState.Downloading(128, 1024)

        val file2 = Attachment.File(
            name = "Surface 2022.03.29_15_15.jpg",
            location = "https://images.unsplash.com/photo-1640622304293-ec9c89c6bac9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80",
            size = 102478568,
            extension = "JPEG"
        )

        file2.state = AttachmentState.Downloading(128, 1024)

        val file3 = Attachment.File(
            name = "Surface 2022.03.29_15_15.jpg",
            location = "https://images.unsplash.com/photo-1640622304293-ec9c89c6bac9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80",
            size = 102478568,
            extension = "JPEG"
        )

        file3.state = AttachmentState.NotDownloaded

        val files = listOf(file1, file2, file3)

        val message = Message()
        message.type = Message.Type.TYPE_FILE
        message.attachment = files

        setContent {
            val theme = themeManger.currentTheme.value
            CompositionLocalProvider(LocalTheme provides theme) {
                Column(modifier = Modifier.padding(80.dp)) {
                    MessageItem(message = message, isOwn = true)
                    MessageItem(message = message, isOwn = false)
                }
            }
        }

    }
}