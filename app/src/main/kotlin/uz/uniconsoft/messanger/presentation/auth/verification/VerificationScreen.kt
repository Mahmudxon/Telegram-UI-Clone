package uz.uniconsoft.messanger.presentation.auth.verification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.uniconsoft.messanger.R
import uz.uniconsoft.messanger.business.domain.util.StyledText
import uz.uniconsoft.messanger.business.domain.util.replaceTags
import uz.uniconsoft.messanger.presentation.theme.Blue500
import uz.uniconsoft.messanger.presentation.theme.Theme

@Composable
fun VerificationScreen(theme: Theme, needPaddingStatusBar: Boolean, onClick : ()->Unit) {
    val actionbarModifier =
        if (needPaddingStatusBar)
            Modifier
                .background(theme.appbarBackgroundColor)
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 25.dp)
        else Modifier
            .background(theme.appbarBackgroundColor)
            .fillMaxWidth()
            .height(56.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.contentBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = actionbarModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(backgroundColor = theme.appbarBackgroundColor),
            ) {
                Icon(Icons.Outlined.ArrowBack, contentDescription = "", tint = Color.White)
            }
            Text(
                text = stringResource(id = R.string.YourCode),
                fontSize = 18.sp,
                color = theme.appbarTextColor,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )
        }


        Icon(
            painter = painterResource(id = R.drawable.ic_devices),
            contentDescription = null,
            tint = theme.primaryTextColor,
            modifier = Modifier
                .height(90.dp)
                .width(60.dp)
                .padding(top = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.SentAppCodeTitle),
            color = theme.chatTextColor,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 24.dp),
            fontWeight = FontWeight.Bold
        )

        StyledText(
            text = replaceTags(
                LocalContext.current,
                stringResource(id = R.string.SentAppCode)
            ),
            textColor = theme.chatTextColor
        )


        TextField(
            value = "", onValueChange = {

            },
            modifier = Modifier
                .width(300.dp)
                .padding(25.dp),
            textStyle = TextStyle(textAlign = TextAlign.Center)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        {
            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(
                onClick =  onClick,
                backgroundColor = Blue500,
                contentColor = Color.White
            ) {
                Icon(Icons.Outlined.Done, "")
            }
        }
    }

}


