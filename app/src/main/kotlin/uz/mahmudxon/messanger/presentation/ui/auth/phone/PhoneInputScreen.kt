package uz.mahmudxon.messanger.presentation.ui.auth.phone

import android.telephony.PhoneNumberUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.mahmudxon.messanger.R
import uz.mahmudxon.messanger.presentation.theme.Blue500
import uz.mahmudxon.messanger.presentation.theme.BreakLine
import uz.mahmudxon.messanger.presentation.theme.Theme

@Composable
fun PhoneInputView(theme: Theme, needPaddingStatusBar: Boolean, navController: NavController) {

    var code by remember { mutableStateOf("+") }
    var phone by remember { mutableStateOf("") }

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
            .background(theme.contentBackgroundColor)
    ) {
        Row(
            modifier = actionbarModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.YourPhone),
                fontSize = 18.sp,
                color = theme.appbarTextColor,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )
//            Button(
//                onClick = {},
//                modifier = Modifier
//                    .fillMaxHeight(),
//                colors = ButtonDefaults.buttonColors(backgroundColor = theme.appbarBackgroundColor)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_baseline_done_24),
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxHeight()
//                )
//            }
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.textButtonColors(Color.Transparent),
                elevation = elevation(defaultElevation = 0.dp)
            )
            {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    text = stringResource(id = R.string.ChooseCountry),
                    fontSize = 14.sp,
                    color = theme.primaryTextColor
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BreakLine)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {

                val colors = TextFieldDefaults.textFieldColors(
                    textColor = theme.primaryTextColor,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Blue500,
                    unfocusedIndicatorColor = BreakLine,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Blue500
                )

                TextField(
                    value = code, onValueChange = {
                        if (it.length < 6)
                            code = if (it.startsWith("+")) it
                            else "+${it.replace("+", "")}"
                    },
                    modifier = Modifier
                        .width(90.dp)
                        .background(Color.Transparent),
                    colors = colors,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                TextField(
                    value = phone,
                    onValueChange = { text ->
                        val formattedPhone =
                            PhoneNumberUtils.formatNumber(
                                code + text.replace(" ", ""),
                                code.replace("+", "")
                            )
                        phone =
                            formattedPhone?.replace(code, "") ?: text
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    colors = colors,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )
            }

            Text(
                text = stringResource(id = R.string.StartText),
                fontSize = 14.sp,
                color = theme.captionColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(
                onClick = {
                    navController.navigate("code")
                },
                backgroundColor = Blue500,
                contentColor = Color.White
            ) {
                Icon(Icons.Outlined.NavigateNext, "")
            }
        }
    }
}