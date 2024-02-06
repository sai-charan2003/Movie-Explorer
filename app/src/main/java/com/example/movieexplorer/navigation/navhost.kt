package com.example.movieexplorer.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieexplorer.screens.details
import com.example.movieexplorer.screens.mainscreen
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.movieexplorer.screens.settings

@Composable
fun navhost(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = Destination.home.route,enterTransition = {
        fadeIn() + slideIntoContainer(
            SlideDirection.Start,
            initialOffset = { 100 },
            animationSpec = (tween(easing = LinearEasing, durationMillis = 200))
        )
    },
        exitTransition = {
            fadeOut() + slideOutOfContainer(
                SlideDirection.Start,
                targetOffset = { -100 },
                animationSpec = (tween(easing = LinearEasing, durationMillis = 200))
            )
        },
        popEnterTransition = {
            fadeIn() + slideIntoContainer(
                SlideDirection.End,
                initialOffset = { -100 },
                animationSpec = (tween(easing = LinearEasing, durationMillis = 200))
            )
        },
        popExitTransition = {
            fadeOut() + slideOutOfContainer(
                SlideDirection.End,
                targetOffset = { 100 },
                animationSpec = (tween(easing = LinearEasing, durationMillis = 200))
            )
        },){
        composable(Destination.home.route){
            mainscreen(navHostController)
        }
        composable(Destination.settings.route){
            settings(navHostController)
        }
        composable(Destination.details.route, arguments = listOf(navArgument("id"){
            type=NavType.IntType
        })){

            it.arguments?.let { it1 -> details(it1.getInt("id"),navHostController) }
        }

    }
}