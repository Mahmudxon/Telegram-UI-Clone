package uz.uniconsoft.messanger.presentation.ui.intro

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import uz.uniconsoft.messanger.business.domain.util.StyledText
import uz.uniconsoft.messanger.business.domain.util.replaceTags

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
                    style = TextStyle(color = Color(0xFF444444)),
                )
                StyledText(
                    text = replaceTags(LocalContext.current, description[page]),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    textColor = Color.Gray
                )
            }

        }
    }
}


