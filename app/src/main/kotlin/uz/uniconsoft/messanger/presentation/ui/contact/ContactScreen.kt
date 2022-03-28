package uz.uniconsoft.messanger.presentation.ui.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import uz.uniconsoft.messanger.business.domain.util.Chat
import uz.uniconsoft.messanger.business.domain.util.DataDummy
import uz.uniconsoft.messanger.presentation.ui.main.Router
import uz.uniconsoft.messanger.presentation.theme.Theme


@Composable
fun ContactScreen(theme: Theme, modifier: Modifier = Modifier) {
    val router = Router.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Contacts") },
                navigationIcon = {
                    IconButton(onClick = {
                        router.navigateUp()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                backgroundColor = theme.appbarBackgroundColor,
                contentColor = theme.appbarTextColor
            )
        },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(theme.windowBackground)
        ) {
            ContractList(theme = theme)
        }
    }
}

@Composable
fun ContractList(
    theme: Theme
) {
    val listChat = DataDummy.listChat
    LazyColumn(modifier = Modifier.background(theme.contentBackgroundColor)) {
        items(listChat.size) { index ->
            Column(Modifier.clickable {

            }) {
                ContactItem(listChat[index])
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(theme.windowBackground)
                )
            }
        }
    }
}

@Composable
fun ContactItem(chat: Chat) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        GlideImage(
            imageModel = chat.imageUrl,
            modifier = Modifier
                .clip(CircleShape)
                .size(56.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .padding(horizontal = 14.dp)
                .weight(7f)
        ) {
            Text(chat.name, fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Last seen recently",
                maxLines = 1,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}
