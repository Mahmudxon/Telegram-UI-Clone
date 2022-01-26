package uz.uniconsoft.messanger.presentation.auth

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import uz.uniconsoft.messanger.business.domain.util.Device
import uz.uniconsoft.messanger.business.domain.util.getDeviceType
import uz.uniconsoft.messanger.presentation.auth.phone.PhoneInputView
import uz.uniconsoft.messanger.presentation.theme.Theme
import uz.uniconsoft.messanger.presentation.theme.ThemeManger
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    @Inject
    lateinit var themeManger: ThemeManger


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val defTheme = if (isSystemInDarkTheme()) Theme.defDark else Theme.defLight
            themeManger.changeTheme(defTheme)
            val theme = themeManger.currentTheme.value
            val boxModifier = if (getDeviceType() == Device.Type.Tablet)
                Modifier
                    .height(450.dp)
                    .width(500.dp)
            else
                Modifier
                    .fillMaxSize()


            Box(Modifier.fillMaxSize()) {

                Image(
                    painter = painterResource(id = theme.chatBackground),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = boxModifier
                    )
                    {
                        PhoneInputView(
                            theme = theme,
                            needPaddingStatusBar = (getDeviceType() == Device.Type.Phone)
                        )
                    }
                }
            }
        }

    }
}
