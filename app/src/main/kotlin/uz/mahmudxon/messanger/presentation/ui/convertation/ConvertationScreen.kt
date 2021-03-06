package uz.mahmudxon.messanger.presentation.ui.convertation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Attachment
import androidx.compose.material.icons.outlined.EmojiEmotions
import androidx.compose.material.icons.outlined.MicNone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
import uz.mahmudxon.messanger.business.domain.util.DataDummy
import uz.mahmudxon.messanger.business.domain.util.getFakeMessages
import uz.mahmudxon.messanger.presentation.theme.BottomSheetShapes
import uz.mahmudxon.messanger.presentation.theme.Theme
import uz.mahmudxon.messanger.presentation.ui.main.Router

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ChatDetailScreen(
    index: Int,
    theme: Theme,
    newMessage: MutableState<String> = remember {
        mutableStateOf("")
    }
) {

    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = { ChatDetailBottomSheet() },
        sheetShape = BottomSheetShapes.medium,
        content = {
            Scaffold(
                topBar = { ChatDetailAppBar(index, theme = theme) },
                backgroundColor = theme.chatBackgroundColor,
                bottomBar = { ChatDetailBottomBar(bottomState, newMessage) },
                content = { ChatDetailBody(theme) },
            )
        }
    )
}

@Composable
private fun ChatDetailAppBar(index: Int, theme: Theme) {

    val chat = DataDummy.listChat[index]
    val router = Router.current

    TopAppBar(
        title = {
            Row(Modifier.padding(vertical = 4.dp)) {
                GlideImage(
                    imageModel = chat.imageUrl,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp),
                    contentScale = ContentScale.Crop
                )
                Column(Modifier.padding(start = 16.dp)) {
                    Text(
                        text = chat.name, fontSize = 16.sp, fontWeight = FontWeight.Bold,
                        color = theme.appbarTextColor
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = "Last seen recently",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        color = theme.appbarCaptionColor
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { router.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            Icon(Icons.Default.MoreVert, contentDescription = null)
        },
        backgroundColor = theme.appbarBackgroundColor,
        contentColor = theme.appbarTextColor
    )
}

@Composable
private fun ChatDetailBody(theme: Theme) {
    val listMessage = getFakeMessages()
    Box {
        Image(
            painter = painterResource(id = theme.chatBackground),
            contentDescription = "chat background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            items(listMessage.size) { index ->
                Spacer(modifier = Modifier.height(8.dp))
                MessageItem(message = listMessage[index], isOwn = index % 2 == 0)
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Spacer(modifier = Modifier.height(56.dp))
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun ChatDetailBottomBar(
    bottomSheetState: ModalBottomSheetState,
    newMessage: MutableState<String>
) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Icon(
            imageVector = Icons.Outlined.EmojiEmotions,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.padding(8.dp)
        )
        BasicTextField(
            value = newMessage.value,
            onValueChange = {
                newMessage.value = it
            },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )
        Icon(
            Icons.Outlined.Attachment,
            tint = Color.LightGray,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    coroutineScope.launch { bottomSheetState.show() }
                }
        )
        Icon(
            Icons.Outlined.MicNone,
            tint = Color.LightGray,
            modifier = Modifier.padding(8.dp),
            contentDescription = null
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun ChatDetailBottomSheet() {
    Column(Modifier.padding(vertical = 16.dp, horizontal = 4.dp)) {

        Box(
            modifier = Modifier
                .height(4.dp)
                .width(24.dp)
                .align(Alignment.CenterHorizontally)
                .background(color = Color.Gray, shape = RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.height(4.dp))

        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 108.dp),
            modifier = Modifier.height(272.dp),
            content = {
                items(13) {
                    GlideImage(
                        imageModel = "https://randomuser.me/api/portraits/men/7.jpg",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        )
        Row(
            Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .background(color = Color.Blue, shape = CircleShape)
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Gallery",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.InsertDriveFile,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .background(color = Color(0xff67B6FF), shape = CircleShape)
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "File",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .background(color = Color(0xFF95EF74), shape = CircleShape)
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Location",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Equalizer,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .background(color = Color(0xFFFED671), shape = CircleShape)
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Poll",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .background(color = Color(0xFFFA7781), shape = CircleShape)
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Video",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}