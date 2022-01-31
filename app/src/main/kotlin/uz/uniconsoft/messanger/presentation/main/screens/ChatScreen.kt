package uz.uniconsoft.messanger.presentation.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
import uz.uniconsoft.messanger.business.domain.util.Chat
import uz.uniconsoft.messanger.business.domain.util.DataDummy
import uz.uniconsoft.messanger.business.domain.util.getStatusBarHeightInDp
import uz.uniconsoft.messanger.presentation.component.AppDrawer
import uz.uniconsoft.messanger.presentation.main.Routes
import uz.uniconsoft.messanger.presentation.theme.BreakLine
import uz.uniconsoft.messanger.presentation.theme.Theme

@Composable
fun ChatScreen(navController: NavHostController, theme: Theme) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Telegram") },
                actions = { Icon(Icons.Default.Search, contentDescription = null) },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                },
                modifier = Modifier.padding(top = LocalContext.current.getStatusBarHeightInDp()),
                backgroundColor = theme.appbarBackgroundColor,
                contentColor = theme.appbarTextColor
            )
        },
        drawerContent = {
            AppDrawer()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Edit, contentDescription = null, tint = Color.White)
            }
        },
        scaffoldState = scaffoldState,
        backgroundColor = theme.appbarBackgroundColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(theme.windowBackground)
        ) {
            ChatList(navController, theme = theme)
        }

    }
}

@Composable
fun ChatList(navController: NavHostController, theme: Theme) {
    val listChat = DataDummy.listChat
    LazyColumn(modifier = Modifier.background(theme.contentBackgroundColor)) {
        items(listChat.size) { index ->
            ChatItem(listChat[index], onClick = {
                navController.navigate(Routes.ChatDetail.route + "/$index")
            })
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(theme.windowBackground)
            )
        }
    }
}

@Composable
fun ChatItem(chat: Chat, onClick: () -> Unit) {
    Row(
        Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clickable { onClick() }) {
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
                chat.lastMessage ?: "",
                maxLines = 1,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        Column(Modifier.weight(1f)) {
            Text(chat.time, fontWeight = FontWeight.Light, fontSize = 13.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chat.newChatSize.toString(),
                color = Color.White,
                fontSize = 13.sp,
                modifier = Modifier
                    .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                    .padding(4.dp),

                )
        }
    }
}




