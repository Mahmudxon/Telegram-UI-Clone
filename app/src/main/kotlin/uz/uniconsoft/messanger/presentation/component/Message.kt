package uz.uniconsoft.messanger.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import uz.uniconsoft.messanger.business.domain.model.Message
import uz.uniconsoft.messanger.business.domain.util.messageFormatter
import uz.uniconsoft.messanger.presentation.theme.Theme

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
fun MessagePhotoItem()
{

}