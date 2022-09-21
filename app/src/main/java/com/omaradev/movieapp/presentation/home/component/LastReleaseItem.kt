package com.omaradev.movieapp.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.lerp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.omaradev.movieapp.R
import com.omaradev.movieapp.presentation.main.BottomNavigationItem
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LastReleaseItem() {
    var items = listOf(
        BottomNavigationItem(
            stringResource(id = R.string.home),
            "home",
            R.drawable.ic_home
        ),
        BottomNavigationItem(
            stringResource(id = R.string.favorite),
            "favorite",
            R.drawable.ic_favorite
        ),
        BottomNavigationItem(
            stringResource(id = R.string.profile),
            "profile",
            R.drawable.ic_person
        )
    )
    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialPage = 2
    )


    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth(),

            ) { page ->
            Card(modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                }
                .padding(10.dp, 0.dp, 10.dp, 0.dp),
                shape = RoundedCornerShape(20.dp)
            ) {

                val newKids = items[page]
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .align(Alignment.Center)
                        .width(180.dp)
                        .height(240.dp)
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.download
                        ),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(180.dp)
                            .height(240.dp)
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(15.dp)
                    ) {
                        Text(
                            text = newKids.name,
                            style = MaterialTheme.typography.h5,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                    }

                }


            }

        }

        HorizontalPagerIndicator(
            pagerState = pagerState, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
                .background(Color.White), indicatorWidth = 0.dp
        )

    }

}

