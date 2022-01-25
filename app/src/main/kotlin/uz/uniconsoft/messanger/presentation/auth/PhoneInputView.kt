package uz.uniconsoft.messanger.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.uniconsoft.messanger.R
import uz.uniconsoft.messanger.presentation.theme.Colors

@Composable
fun PhoneInputView(colors: Colors) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(colors.appbarBackgroundColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.YourPhone),
                fontSize = 18.sp,
                color = colors.appbarTextColor,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(backgroundColor = colors.appbarBackgroundColor)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_done_24),
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight()
                )
            }

        }
    }
}