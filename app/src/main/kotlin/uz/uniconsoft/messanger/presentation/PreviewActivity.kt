package uz.uniconsoft.messanger.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import uz.uniconsoft.messanger.business.domain.model.Attachment
import uz.uniconsoft.messanger.presentation.component.MessagePhotoItem
import uz.uniconsoft.messanger.presentation.component.MessagePhotoItems
import uz.uniconsoft.messanger.presentation.ui.main.states.AttachmentState

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val downloaded = remember {
                mutableStateOf(0L)
            }

            val stateBlur =
                AttachmentState.NotDownloaded

            val state = remember {
                mutableStateOf<AttachmentState>(stateBlur)
            }

            val photo = Attachment.Photo(
                location = "",
                thumbnail = "https://images.unsplash.com/photo-1510272940641-589fcd43e485?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80",
                size = 1024,
                blur = "",
                ratio = ""
            )

            MessagePhotoItem(photo = photo)
            {
                when (state.value) {
                    is AttachmentState.NotDownloaded -> {
                        state.value = AttachmentState.Downloading(512L, 1024L)
                    }
                    is AttachmentState.Downloading -> {
                        state.value = AttachmentState.Downloaded
                    }
                    else -> state.value = AttachmentState.NotDownloaded
                }
            }

        }

    }
}