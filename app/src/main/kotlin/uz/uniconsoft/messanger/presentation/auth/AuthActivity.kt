package uz.uniconsoft.messanger.presentation.auth

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import uz.uniconsoft.messanger.business.domain.util.Device
import uz.uniconsoft.messanger.business.domain.util.getDeviceType
import uz.uniconsoft.messanger.business.domain.util.setStatusBarPadding
import uz.uniconsoft.messanger.presentation.theme.ThemeManger
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    @Inject
    lateinit var themeManger: ThemeManger


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme = themeManger.currentTheme
            val boxModifier = if (getDeviceType() == Device.Type.Tablet)
                Modifier
                    .height(450.dp)
                    .width(500.dp)
                    .background(Color.White)
            else
                Modifier
                    .fillMaxSize()
                    .background(Color.White)



            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(theme.value.chatBackgroundColor)
                    .padding(32.dp),
                //verticalArrangement = Arrangement.Center,
                //horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = boxModifier
                )
                {
                    PhoneInputView(colors = theme.value)
                }
            }
        }

    }
}
