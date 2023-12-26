package com.sendiko.customtabrows.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.customtabrows.TabItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    tabs: List<TabItem>
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState {
        tabs.size
    }
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = tabs[pagerState.currentPage].title,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(
                            16.dp
                        ),
                        fontWeight = FontWeight.Bold
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        containerColor = MaterialTheme.colorScheme.background,
                        divider = {
                            Box(modifier = Modifier.height(0.dp))
                        },
                        indicator = {

                        }
                    ) {
                        tabs.forEachIndexed { index, tabItem ->
                            val boxIndicatorColor by animateColorAsState(
                                targetValue = if (pagerState.currentPage == index)
                                    MaterialTheme.colorScheme.tertiaryContainer
                                else MaterialTheme.colorScheme.background,
                                label = "",
                                animationSpec = tween()
                            )
                            Tab(
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.scrollToPage(index)
                                    }
                                },
                                icon = {
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(boxIndicatorColor)
                                            .padding(horizontal = 16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = if (pagerState.currentPage == index)
                                                tabItem.activeIcon
                                            else tabItem.unActiveIcon,
                                            contentDescription = tabItem.title,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }
                                },
                                selectedContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                unselectedContentColor = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            )
        },
    ) {
        HorizontalPager(
            modifier = Modifier.padding(it),
            state = pagerState
        ) {
            LazyColumn(
                modifier = Modifier
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .fillMaxSize(),
                content = {
                    items(100) { number ->
                        Text(text = number.toString(), modifier = Modifier.padding(8.dp))
                    }
                }
            )
        }
    }
}