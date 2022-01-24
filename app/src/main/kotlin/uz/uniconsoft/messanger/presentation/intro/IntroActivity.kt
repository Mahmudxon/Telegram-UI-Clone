package uz.uniconsoft.messanger.presentation.intro

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import uz.uniconsoft.messanger.R
import uz.uniconsoft.messanger.presentation.auth.AuthActivity
import uz.uniconsoft.messanger.presentation.theme.AppTheme
import uz.uniconsoft.messanger.presentation.util.Device
import uz.uniconsoft.messanger.presentation.util.getDeviceType

@AndroidEntryPoint
@ExperimentalPagerApi
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
            R.drawable.intro1,
            R.drawable.intro2,
            R.drawable.intro3,
            R.drawable.intro4,
            R.drawable.intro5,
            R.drawable.intro6,
            R.drawable.intro7,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
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
                                onclick = {
                                    startActivity(
                                        Intent(
                                            this@IntroActivity,
                                            AuthActivity::class.java
                                        )
                                    )
                                    finish()
                                },
                                icons = icons.asList()
                            )
                        }

                    }

                }
            }
        }
    }
}
