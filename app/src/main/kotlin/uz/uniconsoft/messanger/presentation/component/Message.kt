package uz.uniconsoft.messanger.presentation.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Download
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import uz.uniconsoft.messanger.business.domain.model.Attachment
import uz.uniconsoft.messanger.business.domain.model.Message
import uz.uniconsoft.messanger.business.domain.util.LocalFileManager
import uz.uniconsoft.messanger.business.domain.util.messageFormatter
import uz.uniconsoft.messanger.presentation.theme.Theme
import uz.uniconsoft.messanger.presentation.ui.main.states.AttachmentState

@Composable
fun TextMessageContent(message: Message, theme: Theme, onClick: ((annotationTag: String) -> Unit)) {
    val styledText = messageFormatter(text = message.text, color = theme.linkColor)
    ClickableText(
        text = styledText,
        style = TextStyle(color = theme.chatTextColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {
            styledText.getStringAnnotations(start = it, end = it)
                .firstOrNull()
                ?.let { annotation ->
                    onClick(annotation.tag)
                }
        }
    )
}

@Composable
fun MessagePhotoItem(photo: Attachment.Photo, click: (() -> Unit)) {

    when (val state = photo.state) {
        is AttachmentState.NotDownloaded -> {
            Box(modifier = Modifier.fillMaxWidth())
            {
                GlideImage(imageModel = photo.thumbnail, modifier = Modifier.fillMaxWidth())
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x50FFFFFF))
                )
                {

                    OutlinedButton(
                        onClick = click,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(56.dp),
                        shape = CircleShape
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download Icon",
                        )
                    }

                }
            }
        }
        is AttachmentState.Downloading -> {
            Box(modifier = Modifier.fillMaxWidth())
            {
                GlideImage(imageModel = photo.thumbnail, modifier = Modifier.fillMaxWidth())
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x50FFFFFF))
                )
                {

                    OutlinedButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(56.dp),
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        val infiniteTransition = rememberInfiniteTransition()
                        val angle by infiniteTransition.animateFloat(
                            initialValue = 0F,
                            targetValue = 360F,
                            animationSpec = infiniteRepeatable(
                                animation = tween(2000, easing = LinearEasing)
                            )
                        )
                        val progress = state.currentBytes.toFloat() / state.totalBytes

                        Box {
                            CircularProgressIndicator(
                                progress = if (progress > 0) progress else 0.02f,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .rotate(angle)
                            )
                            Icon(
                                imageVector = Icons.Default.Cancel,
                                contentDescription = "Cancel Icon",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                }
            }
        }
        is AttachmentState.Downloaded -> {
            GlideImage(
                imageModel = LocalFileManager.current.getAttachmentDownloadedPath(photo),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun MessagePhotoItems(images: List<Attachment.Photo>, click: (Attachment.Photo) -> Unit) {
    LazyColumn {
        images.forEach { image ->
            item {
                MessagePhotoItem(photo = image) {
                    click(image)
                }
            }
        }
    }
}