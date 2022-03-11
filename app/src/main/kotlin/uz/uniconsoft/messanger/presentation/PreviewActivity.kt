package uz.uniconsoft.messanger.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import uz.uniconsoft.messanger.presentation.component.MessagePhotoItem
import uz.uniconsoft.messanger.presentation.main.states.AttachmentState

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val infiniteTransition = rememberInfiniteTransition()
            val angle by infiniteTransition.animateFloat(
                initialValue = 0F,
                targetValue = 1024f,
                animationSpec = infiniteRepeatable(
                    animation = tween(20000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )


            val stateBlur =
                AttachmentState.BlurPreview("https://images.unsplash.com/photo-1533167649158-6d508895b680?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8c3BsYXNofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=900&q=60")

            val state = remember {
                mutableStateOf<AttachmentState>(stateBlur)
            }

            MessagePhotoItem(state = state.value) {
                when (state.value) {
                    is AttachmentState.BlurPreview -> {
                        state.value = AttachmentState.Downloading(
                            "https://images.unsplash.com/photo-1533167649158-6d508895b680?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8c3BsYXNofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=900&q=60",
                            angle.toLong(), 1024L
                        )
                    }
                    is AttachmentState.Downloading -> {
                        state.value =
                            AttachmentState.Downloaded("https://images.unsplash.com/photo-1533167649158-6d508895b680?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8c3BsYXNofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=900&q=60")
                    }
                    is AttachmentState.Downloaded -> {
                        state.value =
                            AttachmentState.BlurPreview("https://images.unsplash.com/photo-1533167649158-6d508895b680?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8c3BsYXNofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=900&q=60")
                    }
                }
            }
        }

    }
}