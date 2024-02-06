package com.example.movieexplorer.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieexplorer.ui.theme.MovieExplorerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun settings(navHostController: NavHostController) {
    val Scroll= TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val isdarkmode = sharedPreferences.getBoolean("darkmode", isSystemInDarkTheme())
    var darkmode by remember {
        mutableStateOf(isdarkmode)
    }
    MovieExplorerTheme(darkmode) {
     Scaffold(modifier= Modifier
         .fillMaxSize()
         .nestedScroll(Scroll.nestedScrollConnection),topBar = {
         LargeTopAppBar(title = { Text("Settings")}, scrollBehavior = Scroll, navigationIcon = {
             IconButton(onClick = {navHostController.popBackStack()}) {
                 Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)

             }
         })
     }) {




            Column(
                modifier = Modifier

                    .padding(it)
            ) {
                ListItem({
                    Row( verticalAlignment = Alignment.CenterVertically) {
                        Text("Dark Mode",modifier=Modifier.padding(top=5.dp))
                        Spacer(Modifier.weight(1f))
                        Switch(checked = darkmode, onCheckedChange = {
                            darkmode = it
                            editor.putBoolean("darkmode", it)
                            editor.apply()
                        })

                    }
                })

            }
        }
    }
}