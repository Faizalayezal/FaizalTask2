package com.example.faizaltask2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.faizaltask2.LocalData.ItemViewModel
import com.example.faizaltask2.Screen.CalculationScreen
import com.example.faizaltask2.Screen.ListScreen
import com.example.faizaltask2.ui.theme.FaizalTask2Theme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FaizalTask2Theme {
                App(this@MainActivity)
            }
        }
    }
}

@Composable
fun App(mainActivity: MainActivity) {
    val navController= rememberNavController()

    val context= LocalContext.current


    val itemViewModel: ItemViewModel = viewModel()

    NavHost(navController =navController , startDestination ="screen1" ){
        composable(route="screen1"){
            CalculationScreen(itemViewModel){
                navController.navigate("screen2")
            }
        }
        composable(route="screen2"){
            ListScreen(modifier = Modifier,itemViewModel, popBack = navController::goBack,)
        }

    }
}


private fun NavHostController.goBack() = popBackStack()