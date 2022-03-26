package uz.uniconsoft.messanger.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.uniconsoft.messanger.business.domain.model.Attachment
import uz.uniconsoft.messanger.presentation.component.MessagePhotoItems
import uz.uniconsoft.messanger.presentation.ui.main.states.AttachmentState

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val stateBlur =
                AttachmentState.NotDownloaded

            val state = remember {
                mutableStateOf<AttachmentState>(stateBlur)
            }

            val photos =
                listOf(
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

            Column(modifier = Modifier.width(200.dp)) {
                MessagePhotoItems(images = photos)
                {
                    when (it.state) {
                        is AttachmentState.NotDownloaded -> it.state =
                            AttachmentState.Downloading(512, 1024)
                        is AttachmentState.Downloading -> it.state = AttachmentState.Downloaded
                        else -> it.state = AttachmentState.NotDownloaded
                    }
                }
            }

        }

    }
}