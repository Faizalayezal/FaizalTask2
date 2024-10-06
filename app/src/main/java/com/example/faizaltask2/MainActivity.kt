package com.example.faizaltask2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.faizaltask2.LocalData.ItemViewModel
import com.example.faizaltask2.Screen.CalculationScreen
import com.example.faizaltask2.Screen.NoteListScreen
import com.example.faizaltask2.ui.theme.FaizalTask2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val itemViewModel by viewModels<ItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FaizalTask2Theme {
                App(this@MainActivity,itemViewModel)
            }
        }
    }
}

@Composable
fun App(mainActivity: MainActivity, itemViewModel: ItemViewModel) {
    val navController= rememberNavController()


    NavHost(navController =navController , startDestination ="screen1" ){
        composable(route="screen1"){
            CalculationScreen(itemViewModel){
                navController.navigate("screen2")
            }
        }
        composable(route="screen2"){
            NoteListScreen(itemViewModel)
           // ListScreen(modifier = Modifier,itemViewModel, popBack = navController::goBack,)
        }

    }
}


private fun NavHostController.goBack() = popBackStack()