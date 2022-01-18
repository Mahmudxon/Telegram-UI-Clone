package uz.uniconsoft.messanger.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import uz.uniconsoft.messanger.R
import uz.uniconsoft.messanger.business.domain.util.Device
import uz.uniconsoft.messanger.business.domain.util.getDeviceType

@ExperimentalPagerApi
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val titles = arrayOf<String>(
            getString(R.string.Page1Title),
            getString(R.string.Page2Title),
            getString(R.string.Page3Title),
            getString(R.string.Page5Title),
            getString(R.string.Page4Title),
            getString(R.string.Page6Title)
        )
        val messages = arrayOf<String>(
            getString(R.string.Page1Message),
            getString(R.string.Page2Message),
            getString(R.string.Page3Message),
            getString(R.string.Page5Message),
            getString(R.string.Page4Message),
            getString(R.string.Page6Message)
        )

        setContent {
            Column( modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (getDeviceType()) {
                    Device.Type.Phone -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                        )
                        {
                            IntroPagerView(
                                messages = titles.toList(),
                                description = messages.toList()
                            )
                            IntroLottieView()
                        }
                    }

                    Device.Type.Tablet -> {
                        Box(
                            modifier = Modifier
                                .height(500.dp)
                                .width(500.dp)
                                .background(Color.White)
                        )
                        {
                            IntroPagerView(
                                messages = titles.toList(),
                                description = messages.toList()
                            )
                            IntroLottieView()
                        }
                    }
                }
            }
        }
    }


}