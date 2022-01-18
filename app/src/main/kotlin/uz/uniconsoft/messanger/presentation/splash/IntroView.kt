package uz.uniconsoft.messanger.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import uz.uniconsoft.messanger.R

@ExperimentalPagerApi
@Composable
fun IntroPagerView(
    messages: List<String> = listOf(),
    description: List<String> = listOf()
) {
    HorizontalPager(count = messages.size, Modifier.fillMaxSize()) { page ->
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
                    .weight(1f)
            ) {

                Text(
                    textAlign = TextAlign.Center,
                    text = messages[page],
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = description[page],
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}


@Composable
fun IntroLottieView() {
    Column(modifier = Modifier.fillMaxSize())
    {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.ic_telegram),
                "content description",
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}


