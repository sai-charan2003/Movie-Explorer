package com.example.movieexplorer.screens

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieexplorer.data.network.Apiservice
import com.example.movieexplorer.navigation.Destination
import com.example.movieexplorer.ui.theme.MovieExplorerTheme
import com.example.movieexplorer.utils.loadMoreItems
import com.example.movieexplorer.utils.task
import com.example.movieexplorer.viewmodel.moviesviewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainscreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val Scroll= TopAppBarDefaults.pinnedScrollBehavior()
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val isdarkmode = sharedPreferences.getBoolean("darkmode", isSystemInDarkTheme())

    MovieExplorerTheme(isdarkmode) {


        var showmenu by remember {
            mutableStateOf(false)
        }
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .nestedScroll(Scroll.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text("Movies Explorer") },
                    scrollBehavior = Scroll,
                    actions = {
                        IconButton(onClick = { showmenu = !showmenu }) {
                            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)


                        }
                        DropdownMenu(expanded = showmenu, onDismissRequest = { showmenu = false }) {
                            DropdownMenuItem(
                                text = { Text(text = "Settings") },
                                onClick = {
                                    navHostController.navigate(Destination.settings.route)


                                    showmenu = false

                                })


                        }

                    }

                )

            }) { innerPadding ->
            val context = LocalContext.current

            val coroutine = CoroutineScope(Dispatchers.IO)
            val viewmodel = viewModel<moviesviewmodel>(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return moviesviewmodel(
                            context
                        ) as T
                    }
                }
            )
            val lazyListState = rememberLazyListState()
            var isLoading by remember { mutableStateOf(false) }
            val buffer = 1
            val reachedBottom: Boolean by remember {
                derivedStateOf {
                    lazyListState.layoutInfo.visibleItemsInfo
                        .lastOrNull { it.offset > 0 } // Find the last visible item with a positive offset
                        ?.index == lazyListState.layoutInfo.totalItemsCount - 1
                }
            }
            Log.d("TAG", "mainscreen: $reachedBottom")

            LaunchedEffect(lazyListState) {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .build()
                    .create(Apiservice::class.java)

                val response = retrofit.getmovieslist(1)
                Log.d("TAG", "onCreate: ${response.body()}")
                response.body()?.let { viewmodel.insert(it.results) }

                task(context)
                if (reachedBottom) loadMoreItems(viewmodel,context)
            }






            val movieslist by viewmodel.allmovies.observeAsState()
            val sortedMovies = movieslist?.sortedByDescending {
                it.release_date
            }
            Log.d("TAG", "mainscreen: $sortedMovies")
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                sortedMovies?.let {
                    items(it.size) {
                        ListItem({
                            Row(modifier = Modifier.fillMaxSize()) {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w185${sortedMovies!![it].poster_path}",
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(100.dp)
                                        .clip(RoundedCornerShape(5.dp))
                                )
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center
                                ) {


                                    Text(
                                        sortedMovies!![it].title,
                                        modifier= Modifier.fillMaxSize(),
                                        style = MaterialTheme.typography.titleLarge



                                    )
                                    Text(
                                        sortedMovies!![it].release_date,
                                        modifier= Modifier
                                            .fillMaxSize()
                                            .padding(top = 10.dp),
                                        style = MaterialTheme.typography.labelLarge


                                    )
                                }
                            }
                        }, modifier = Modifier.clickable {

                            navHostController.navigate("details/${sortedMovies!![it].id}")

                        })
                        Divider()

                    }
                }
                item{
                    Column(modifier= Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {


                        CircularProgressIndicator()
                    }
                    LaunchedEffect(key1 = Unit) {
                        loadMoreItems(viewmodel,context)


                    }

                }
            }


        }
    }
}