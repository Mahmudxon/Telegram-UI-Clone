package uz.mahmudxon.messanger.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.runtime.Composable
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
import uz.mahmudxon.messanger.presentation.ui.main.Router
import uz.mahmudxon.messanger.presentation.ui.main.Routes
import uz.mahmudxon.messanger.presentation.theme.Theme

@Composable
fun AppDrawer(theme: Theme) {
    val router = Router.current
    Column(
        modifier = Modifier
            .background(theme.menuBackColor)
            .fillMaxHeight()
    ) {
        DrawerHeader(theme = theme)

        DrawerMenuItem(
            icon = R.drawable.ic_people,
            text = "New Group",
            theme = theme
        )
        DrawerMenuItem(
            icon = R.drawable.ic_person,
            text = "Contacts",
            onClick = {
                router.navigate(Routes.Contact.route)
            },
            theme = theme
        )
        DrawerMenuItem(
            icon = R.drawable.ic_call,
            text = "Calls",
            theme = theme
        )
        DrawerMenuItem(
            icon = R.drawable.ic_bookmark,
            text = "Saved Messages",
            theme = theme
        )
        DrawerMenuItem(
            icon = R.drawable.ic_settings,
            text = "Settings",
            onClick = {
                router.navigate(Routes.Setting.route)
            },
            theme = theme
        )
        Divider()
        DrawerMenuItem(
            icon = R.drawable.ic_person_add,
            text = "Invite Friends",
            theme = theme
        )
        DrawerMenuItem(
            icon = R.drawable.ic_help,
            text = "Telegram Features",
            theme = theme
        )
    }
}

@Composable
fun DrawerHeader(theme: Theme) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(168.dp)
            .background(color = theme.appbarBackgroundColor)
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom, modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
        ) {
            GlideImage(
                imageModel = "https://randomuser.me/api/portraits/men/12.jpg",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(56.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text("Kirdir Kirdirovich", color = Color.White, fontWeight = FontWeight.Medium)
            Text(
                "+777 7777 777 777",
                fontWeight = FontWeight.Light,
                color = Color.White,
                fontSize = 14.sp
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(30.dp)
        ) {
            Icon(
                imageVector = if (theme.isDark)
                    Icons.Default.LightMode
                else Icons.Default.ModeNight,
                contentDescription = null,
                tint = theme.appbarTextColor,
                modifier = Modifier.clickable {
                    theme.manager.toggle()
                }
            )
        }
    }
}

@Composable
private fun DrawerMenuItem(
    icon: Int,
    text: String,
    onClick: () -> Unit = {},
    theme: Theme
) {
    Column(modifier = Modifier.clickable { onClick() }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = theme.menuIconColor,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(28.dp))
            Text(
                text = text,
                fontWeight = FontWeight.Medium,
                color = theme.menuTextColor
            )
        }
    }
}