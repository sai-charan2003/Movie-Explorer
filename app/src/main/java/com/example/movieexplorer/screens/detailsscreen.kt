package com.example.movieexplorer.screens

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieexplorer.data.network.detailsapi
import com.example.movieexplorer.data.model.model.moviedetials
import com.example.movieexplorer.ui.theme.MovieExplorerTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun details(id:Int,navHostController: NavHostController) {
    val Scroll= TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val isdarkmode = sharedPreferences.getBoolean("darkmode", isSystemInDarkTheme())
    MovieExplorerTheme(isdarkmode) {



        var darkmode by remember {
            mutableStateOf(isdarkmode)
        }
        var moviedetails by remember {
            mutableStateOf<moviedetials?>(null)
        }
        LaunchedEffect(key1 = Unit) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(detailsapi::class.java)
            val reponse = retrofit.getMovieDetails(id, "3aa2c997ec3eb7851fa0e377b062b620")
            moviedetails = reponse
            Log.d("TAG", "details: $reponse")

        }
        Surface {
            Scaffold(modifier= Modifier
                .fillMaxSize()
                .nestedScroll(Scroll.nestedScrollConnection),topBar = {
                TopAppBar(title = { Text("Details") }, scrollBehavior = Scroll, navigationIcon = {
                    IconButton(onClick = {navHostController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)

                    }
                })
            }) {


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),

                    ) {
                    item {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${moviedetails?.poster_path}",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp)
                                .height(300.dp)
                                .width(300.dp)
                                .clip(shape = RoundedCornerShape(10.dp)),
                            alignment = Alignment.Center

                        )
                        moviedetails?.let {
                            Text(
                                it.title,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Release Date:")
                            moviedetails?.let { Text(it.release_date) }
                        }
                        Row(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Overview:")
                            moviedetails?.let { Text(it.overview) }
                        }
                        Row(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Runtime:")
                            moviedetails?.let { Text(it.runtime + " Minutes") }
                        }
                        Row(modifier = Modifier.padding(10.dp)) {
                            Text(text = "Genres:")
                            moviedetails?.genres?.let { genres ->
                                Text(
                                    text = genres.joinToString(", ") { it.name },
                                    modifier = Modifier.padding(end = 2.dp)
                                )
                            }
                        }


                    }
                }
            }
        }


    }
}