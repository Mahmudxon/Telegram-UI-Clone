package uz.mahmudxon.messanger.presentation.ui.convertation

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uz.mahmudxon.messanger.R
import uz.mahmudxon.messanger.business.domain.model.Attachment
import uz.mahmudxon.messanger.business.domain.model.Message
import uz.mahmudxon.messanger.business.domain.util.messageFormatter
import uz.mahmudxon.messanger.business.domain.util.toStringAsFileSize
import uz.mahmudxon.messanger.presentation.theme.LocalTheme
import uz.mahmudxon.messanger.presentation.theme.Theme
import java.util.*

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
fun MessageVoiceItem(
    audio: Attachment.Voice,
    onClick: () -> Unit,
    iconBackColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(56.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = iconBackColor),
            contentPadding = PaddingValues(0.dp)
        ) {
            when (val state = audio.state) {
                is Attachment.AttachmentState.NotDownloaded -> {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Download Icon",
                    )
                }
                is Attachment.AttachmentState.Downloading -> {
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
                else -> {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Download Icon",
                    )
                }
            }
        }

        Slider(
            value = 0.5f, onValueChange = {

            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MessagePhotoItem(photo: Attachment.Photo, click: (() -> Unit)) {
    when (val state = photo.state) {
        is Attachment.AttachmentState.NotDownloaded,
        is Attachment.AttachmentState.Downloading -> {
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

                    if (state is Attachment.AttachmentState.NotDownloaded) {
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
                    } else if (state is Attachment.AttachmentState.Downloading) {
                        OutlinedButton(
                            onClick = click,
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
        is Attachment.AttachmentState.Downloaded -> {
            AsyncImage(
                model = photo.thumbnail, // LocalFileManager.current.getAttachmentDownloadedPath(photo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = click),
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
fun MessageFileItem(
    file: Attachment.File,
    titleColor: Color,
    captionColor: Color,
    click: () -> Unit
) {

    val state = file.state

    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .background(
                    color = titleColor.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(5.dp)
                ),
        ) {
            Button(
                onClick = click,
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .align(Alignment.Center),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = titleColor)
            ) {

                Box(modifier = Modifier.fillMaxSize()) {
                    val icon = when (state) {
                        is Attachment.AttachmentState.Downloading -> {
                            val infiniteTransition = rememberInfiniteTransition()
                            val angle by infiniteTransition.animateFloat(
                                initialValue = 0F,
                                targetValue = 360F,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(2000, easing = LinearEasing)
                                )
                            )
                            val progress = state.currentBytes.toFloat() / state.totalBytes

                            CircularProgressIndicator(
                                progress = if (progress > 0) progress else 0.02f,
                                modifier = Modifier
                                    .rotate(angle)
                                    .fillMaxSize()
                                    .align(Alignment.Center),
                                color = Color.White
                            )

                            Icons.Default.Cancel
                        }
                        Attachment.AttachmentState.Downloaded -> Icons.Default.InsertDriveFile
                        else -> Icons.Default.Download
                    }

                    Icon(
                        imageVector = icon,
                        contentDescription = "File Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(24.dp)
                            .width(24.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = file.name, color = titleColor, fontSize = 15.sp)
            Text(
                text = "${file.size.toStringAsFileSize()} | ${file.extension.uppercase(Locale.getDefault())}",
                color = captionColor,
                fontSize = 13.sp
            )
        }
    }
}


@Composable
fun MessageFileItems(
    files: List<Attachment.File>,
    titleColor: Color,
    captionColor: Color,
    click: (Attachment.File) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        files.forEach { file ->
            Spacer(modifier = Modifier.height(2.dp))
            MessageFileItem(file = file, titleColor = titleColor, captionColor = captionColor) {
                click(file)
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
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.End
    ) {
        when (message.type) {
            Message.Type.TYPE_PHOTO -> {
                val photos = message.attachment.map { it as Attachment.Photo }
                MessagePhotoItems(images = photos, click = attachmentClick)
            }
            Message.Type.TYPE_FILE -> {
                val files = message.attachment.map { it as Attachment.File }
                val titleColor =
                    if (isOwnMessage) theme.ownChatAttachmentTitle else theme.partnerChatAttachmentTitle
                MessageFileItems(
                    files = files,
                    titleColor = titleColor,
                    captionColor = theme.captionColor,
                    click = attachmentClick
                )
            }
        }

        if (message.type != Message.Type.TYPE_TEXT && message.text.isNotEmpty())
            Spacer(modifier = Modifier.height(16.dp))

        if (message.text.isNotEmpty())
            TextMessageContent(message = message, theme = theme, onClick = annotationClick)

        Row {
            Text(
                text = "16:20 PM",
                color = captionColor,
                modifier = Modifier.padding(end = 8.dp),
                fontSize = 12.sp
            )
            val statusIcon = when (message.status) {
                Message.Status.STATUS_SENT -> Icons.Default.Done
                Message.Status.STATUS_SEEN -> Icons.Default.DoneAll
                Message.Status.STATUS_WAITING -> Icons.Default.Timer
                else -> Icons.Default.Error
            }

            val statusIconColor =
                if (message.status == Message.Status.STATUS_ERROR)
                    Color.Red else captionColor

            Icon(
                imageVector = statusIcon,
                contentDescription = "Message Status",
                modifier = Modifier.width(16.dp),
                tint = statusIconColor
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
    val theme = LocalTheme.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 500.dp)
                .fillMaxWidth(0.85f),
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
                attachmentClick = attachmentClick,
                isOwnMessage = true
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_own_message_shape),
            contentDescription = "shape",
            tint = theme.ownChatBackgroundColor
        )

    }
}


@Composable
fun FriendMessage(
    message: Message,
    annotationClick: ((annotationTag: String, annotationItem: String) -> Unit) = { _, _ -> },
    attachmentClick: ((attachment: Attachment) -> Unit) = {}
) {
    val theme = LocalTheme.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_friend_messanger_shape),
            contentDescription = "shape",
            tint = theme.partnerChatBackgroundColor
        )

        Card(
            modifier = Modifier
                .widthIn(max = 500.dp)
                .fillMaxWidth(0.85f),
            backgroundColor = theme.partnerChatBackgroundColor,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomEnd = 16.dp,
            )
        ) {
            MessageContent(
                message = message,
                theme = theme,
                annotationClick = annotationClick,
                attachmentClick = attachmentClick,
                isOwnMessage = false
            )
        }

    }
}

@Composable
fun MessageItem(message: Message, isOwn: Boolean) {
    if (isOwn)
        OwnMessage(message = message)
    else FriendMessage(message = message)
}