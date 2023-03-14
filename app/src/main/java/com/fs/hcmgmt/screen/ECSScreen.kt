package com.fs.hcmgmt.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.fs.hcmgmt.R
import com.fs.hcmgmt.data.Server
import com.fs.hcmgmt.util.ECSOperationMethod
import com.fs.hcmgmt.util.ECSStatus
import com.fs.hcmgmt.viewmodel.ECSViewModel
import com.fs.hcmgmt.viewmodel.LoginViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ECSScreen() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (title,
            logoutButton,
            ecsList) = createRefs()
        val ecsViewModel: ECSViewModel = hiltViewModel()
        val loginViewModel: LoginViewModel = hiltViewModel()
        val ecsQueryListState by ecsViewModel.state.collectAsState()
        val lazyListState = rememberLazyListState()
        val refreshState = rememberPullRefreshState(
            refreshing = ecsQueryListState.isRefreshing,
            onRefresh = {
                ecsViewModel.queryECS()
            })

        Text(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    linkTo(start = parent.start, end = parent.end)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }
                .padding(8.dp),
            text = stringResource(R.string.title_ecs_screen),
            style = TextStyle(fontStyle = FontStyle.Italic),
            fontWeight = FontWeight.Bold
        )

        Button(
            modifier = Modifier.constrainAs(logoutButton) {
                top.linkTo(parent.top, 8.dp)
                end.linkTo(parent.end, 8.dp)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }, // Occupy the max size in the Compose UI tree
            onClick = {
                loginViewModel.logout()
            }) {
            Text(text = stringResource(R.string.logout_button_text), fontSize = 8.sp)
        }

        Box(
            modifier = Modifier
                .constrainAs(ecsList) {
                    top.linkTo(logoutButton.bottom, 16.dp)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 70.dp),
                state = lazyListState,
                modifier = Modifier
                    .pullRefresh(refreshState)
                    .fillMaxSize()
            ) {
                ecsQueryListState.queryResult.data?.let { data ->
                    items(data.count.toInt(), itemContent = {
                        BuildECSCard(data.servers[it], ecsViewModel)
                    })
                }
            }

            PullRefreshIndicator(
                ecsQueryListState.isRefreshing,
                refreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BuildECSCard(server: Server, ecsViewModel: ECSViewModel) {
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val (card) = createRefs()
        var showOptions by remember { mutableStateOf(false) }

        Card(
            modifier = Modifier
                .constrainAs(card) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
                .combinedClickable(
                    onClick = { },
                    onLongClick = { showOptions = true }
                ),
            backgroundColor = Color("#C8BFE7".toColorInt()),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
        ) {
            ConstraintLayout(Modifier.fillMaxSize()) {
                val (menu, name, status, statusMore) = createRefs()

                DropdownMenu(
                    expanded = showOptions,
                    onDismissRequest = { showOptions = false },
                    modifier = Modifier
                        .constrainAs(menu) {}
                        .then(Modifier.background(color = Color("#A596D8".toColorInt())))
                ) {
                    if (server.status in listOf(
                            ECSStatus.BUILD.name,
                            ECSStatus.ERROR.name,
                            ECSStatus.SHUTOFF.name,
                        )
                    ) {
                        DropdownMenuItem(onClick = {
                            ecsViewModel.operationECS(listOf(server.id), ECSOperationMethod.START)
                            showOptions = false
                        }) {
                            Text(stringResource(id = R.string.ecs_menu_start))
                        }

                        DropdownMenuItem(onClick = {
                            ecsViewModel.operationECS(listOf(server.id), ECSOperationMethod.RESTART)
                            showOptions = false
                        }) {
                            Text(stringResource(id = R.string.ecs_menu_restart))
                        }
                    } else {
                        DropdownMenuItem(onClick = {
                            ecsViewModel.operationECS(listOf(server.id), ECSOperationMethod.STOP)
                            showOptions = false
                        }) {
                            Text(stringResource(id = R.string.ecs_menu_stop))
                        }
                    }
                }

                server.metadata.ecmResStatus?.let {
                    Text(
                        text = server.metadata.ecmResStatus,
                        color = Color.Red,
                        fontSize = 8.sp,
                        modifier = Modifier
                            .constrainAs(statusMore) {
                                end.linkTo(parent.end, 8.dp)
                                bottom.linkTo(parent.bottom, 8.dp)
                            }
                    )
                }

                Text(
                    text = server.status,
                    color = Color.DarkGray,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .constrainAs(status) {
                            end.linkTo(parent.end, 8.dp)
                            bottom.linkTo(statusMore.top, 4.dp)
                        }
                        .then(Modifier.padding(top= 8.dp))
                )

                Text(
                    text = server.name,
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .constrainAs(name) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}