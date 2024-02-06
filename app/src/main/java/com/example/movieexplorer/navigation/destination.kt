package com.example.movieexplorer.navigation

sealed class Destination(val route:String){
    object home:Destination("Home")
    object details:Destination("details/{id}")
    object settings:Destination("settings")

}