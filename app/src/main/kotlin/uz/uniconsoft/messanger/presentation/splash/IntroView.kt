package uz.uniconsoft.messanger.presentation.splash

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun IntroPagerView(
    pagerState: PagerState,
    messages: List<String> = listOf(),
    description: List<String> = listOf()
) {
    HorizontalPager(count = messages.size, Modifier.fillMaxSize(), state = pagerState) { page ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
        {

            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {

                Text(
                    textAlign = TextAlign.Center,
                    text = messages[page],
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    style = TextStyle()
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = description[page],
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 15.sp
                )
            }

        }
    }
}


@ExperimentalPagerApi
@Composable
fun IntroLottieView(onclick: () -> Unit, pagerState: PagerState, icons: List<Int>) {
    Column(modifier = Modifier.fillMaxSize())
    {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var isPlaying by remember {
                mutableStateOf(false)
            }

            LaunchedEffect(pagerState) {
                isPlaying = true
            }

            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(icons[pagerState.currentPage])
            )
            val progress by animateLottieCompositionAsState(
                composition = composition,
                isPlaying = isPlaying
            )
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier
                    .padding(16.dp)
                    .height(90.dp)
                    .width(90.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .padding(
                    start = 40.dp,
                    top = 80.dp,
                    bottom = 40.dp,
                    end = 40.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onclick, modifier = Modifier
                    .height(40.dp)
                    .padding(0.dp)
                    .fillMaxWidth()
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp))
                )
                {

                }
            }
        }
    }
}


