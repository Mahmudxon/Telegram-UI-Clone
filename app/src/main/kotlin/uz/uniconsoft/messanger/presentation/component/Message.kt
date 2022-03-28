package uz.uniconsoft.messanger.presentation.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uz.uniconsoft.messanger.business.domain.model.Attachment
import uz.uniconsoft.messanger.business.domain.model.Message
import uz.uniconsoft.messanger.business.domain.util.messageFormatter
import uz.uniconsoft.messanger.presentation.theme.LocalThemeManager
import uz.uniconsoft.messanger.presentation.theme.Theme
import uz.uniconsoft.messanger.presentation.ui.main.states.AttachmentState

@Composable
fun TextMessageContent(
    message: Message,
    theme: Theme,
    onClick: ((annotationTag: String, annotationItem: String) -> Unit)
) {
    val styledText = messageFormatter(text = message.text, color = theme.linkColor)
    ClickableText(
        text = styledText,
        style = TextStyle(color = theme.chatTextColor),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            styledText.getStringAnnotations(start = it, end = it)
                .firstOrNull()
                ?.let { annotation ->
                    onClick(annotation.tag, annotation.item)
                }
        }
    )
}

@Composable
fun MessagePhotoItem(photo: Attachment.Photo, click: (() -> Unit)) {

    when (val state = photo.state) {
        is AttachmentState.NotDownloaded,
        is AttachmentState.Downloading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 60.dp)
            )
            {
                AsyncImage(
                    model = photo.thumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillWidth
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color(0xE0212427))
                )
                {

                    if (state is AttachmentState.NotDownloaded) {
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
                    } else if (state is AttachmentState.Downloading) {
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
        }
        is AttachmentState.Downloaded -> {
            AsyncImage(
                model = photo.thumbnail, // LocalFileManager.current.getAttachmentDownloadedPath(photo),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
fun MessagePhotoItems(images: List<Attachment.Photo>, click: (Attachment.Photo) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val startIndex = if (images.size % 2 == 0) {
            0
        } else {

            MessagePhotoItem(photo = images[0]) {
                click(images[0])
            }
            1
        }

        for (x in startIndex until images.size step 2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    MessagePhotoItem(photo = images[x]) {
                        click(images[x])
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    MessagePhotoItem(photo = images[x + 1]) {
                        click(images[x + 1])
                    }
                }
            }
        }
    }
}

@Composable
fun MessageContent(
    message: Message, theme: Theme,
    annotationClick: ((annotationTag: String, annotationItem: String) -> Unit),
    attachmentClick: ((attachment: Attachment) -> Unit),
    isOwnMessage: Boolean = false
) {

    val captionColor = if (isOwnMessage) theme.chatOwnCaption else theme.chatCaption

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.End
    ) {
        when (message.type) {
            Message.Type.TYPE_PHOTO -> {
                val photos = message.attachment.map { it as Attachment.Photo }
                MessagePhotoItems(images = photos, click = attachmentClick)
            }
        }

        if (message.type != Message.Type.TYPE_TEXT)
            Spacer(modifier = Modifier.height(16.dp))

        TextMessageContent(message = message, theme = theme, onClick = annotationClick)

        Row {
            Text(text = "16:20 PM", color = captionColor, modifier = Modifier.padding(end = 8.dp), fontSize = 12.sp)
            val statusIcon = when (message.status) {
                Message.Status.STATUS_SENT -> Icons.Default.Done
                Message.Status.STATUS_SEEN -> Icons.Default.DoneAll
                Message.Status.STATUS_WAITING -> Icons.Default.Timer
                else -> Icons.Default.Error
            }

            Icon(
                imageVector = statusIcon,
                contentDescription = "Message Status",
                modifier = Modifier.width(16.dp),
                tint = captionColor
            )
        }
    }
}


@Composable
fun OwnMessage(
    message: Message,
    annotationClick: ((annotationTag: String, annotationItem: String) -> Unit) = { _, _ -> },
    attachmentClick: ((attachment: Attachment) -> Unit) = {}
) {
    val theme = LocalThemeManager.current.currentTheme.value
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            backgroundColor = theme.ownChatBackgroundColor,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = 16.dp,
            )
        ) {
            MessageContent(
                message = message,
                theme = theme,
                annotationClick = annotationClick,
                attachmentClick = attachmentClick
            )
        }
    }
}