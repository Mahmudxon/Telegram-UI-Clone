package uz.uniconsoft.messanger.presentation.intro

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
import com.google.accompanist.pager.rememberPagerState
import uz.uniconsoft.messanger.R
import uz.uniconsoft.messanger.business.domain.util.Device
import uz.uniconsoft.messanger.business.domain.util.getDeviceType
import uz.uniconsoft.messanger.presentation.theme.MessangerTheme

@ExperimentalPagerApi
@SuppressLint("CustomSplashScreen")
class IntroActivity : AppCompatActivity() {

    private val titles by lazy {
        arrayOf(
            getString(R.string.Page1Title),
            getString(R.string.Page2Title),
            getString(R.string.Page3Title),
            getString(R.string.Page5Title),
            getString(R.string.Page4Title),
            getString(R.string.Page6Title)
        )
    }
    private val messages by lazy {
        arrayOf(
            getString(R.string.Page1Message),
            getString(R.string.Page2Message),
            getString(R.string.Page3Message),
            getString(R.string.Page5Message),
            getString(R.string.Page4Message),
            getString(R.string.Page6Message)
        )
    }

    private val icons by lazy {
        arrayOf(
            R.raw.telegram,
            R.raw.rocket,
            R.raw.gift,
            R.raw.strong,
            R.raw.security,
            R.raw.cloud
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessangerTheme {
                val pagerState = rememberPagerState()
                val boxModifier = if (getDeviceType() == Device.Type.Tablet)
                    Modifier
                        .height(450.dp)
                        .width(500.dp)
                        .background(Color.White)
                else
                    Modifier
                        .fillMaxSize()
                        .background(Color.White)

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = boxModifier
                    )
                    {

                        IntroPagerView(
                            pagerState = pagerState,
                            messages = titles.toList(),
                            description = messages.toList()
                        )
                        IntroLottieView(
                            pagerState = pagerState,
                            onclick = {},
                            icons = icons.asList()
                        )
                    }

                }

            }
        }
    }
}
