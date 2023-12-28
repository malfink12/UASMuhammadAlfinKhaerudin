package com.example.uasmuhammadalfinkhaerudin

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.uasmuhammadalfinkhaerudin.presentation.MainViewModel
import com.example.uasmuhammadalfinkhaerudin.presentation.SplashViewModel
import com.example.uasmuhammadalfinkhaerudin.presentation.home.navigation.AppNavigation
import com.example.uasmuhammadalfinkhaerudin.ui.theme.UASMuhammadAlfinKhaerudinTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //@RequiresApi(Build.VERSION_CODES.O)

    private val mainViewModel by viewModels<MainViewModel>()
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{viewModel.isLoading.value}
        super.onCreate(savedInstanceState)
        setContent {
            UASMuhammadAlfinKhaerudinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(mainViewModel = mainViewModel)
                }
            }
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/

 //Function Main
//@OptIn(ExperimentalMaterial3Api::class)
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun Main() {
//    // Take day from system
//    var day by remember { mutableStateOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) }
//    // Convert day to indonesian language
//    val dayIndoVersion = when (day) {
//        Calendar.MONDAY -> "Senin"
//        Calendar.TUESDAY -> "Selasa"
//        Calendar.WEDNESDAY -> "Rabu"
//        Calendar.THURSDAY -> "Kamis"
//        Calendar.FRIDAY -> "Jumat"
//        Calendar.SATURDAY -> "Sabtu"
//        Calendar.SUNDAY -> "Minggu"
//        else -> "Unknown"
//    }
//    val listDays = mapOf(
//        Calendar.MONDAY to "Senin",
//        Calendar.TUESDAY to "Selasa",
//        Calendar.WEDNESDAY to "Rabu",
//        Calendar.THURSDAY to "Kamis",
//        Calendar.FRIDAY to "Jumat",
//        Calendar.SATURDAY to "Sabtu",
//        Calendar.SUNDAY to "Minggu"
//    )
//    val sheetState = rememberModalBottomSheetState()
//    val scope = rememberCoroutineScope()
//    var showBottomSheet by remember { mutableStateOf(false) }
//    Scaffold { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//            TopAppBar(nameApp = "JAPEL", day = dayIndoVersion, action = { showBottomSheet = true })
//
//            // Content jadwal
//            ContentJadwal(pukul = "08.00 - 10.00", jadwal = "Pemrograman Mobile")
//
//            if (showBottomSheet) {
//                ModalBottomSheet(
//                    onDismissRequest = { showBottomSheet = false },
//                    sheetState = sheetState
//                ) {
//                    Column {
//                        listDays.forEach { (key, value) ->
//                            Button(
//                                onClick = {
//                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
//                                        if (!sheetState.isVisible) {
//                                            showBottomSheet = false
//                                        }
//                                        day = key
//                                    }
//                                },
//                                colors = ButtonDefaults.buttonColors(
//                                    containerColor = Color.Magenta,
//                                    contentColor = Color.White
//                                ),
//                                // Kecilin buttonnya
//                                modifier = Modifier.height(30.dp),
//                                shape = CutCornerShape(percent = 25)
//                            ) {
//                                Text(text = value)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//}

//// Function for navbars
@Composable
fun TopAppBar(nameApp: String, day: String, action: () -> Unit = {}) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            // Kasih background color
            .background(Color(android.graphics.Color.parseColor("#FFE6E6")))
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "$nameApp.edu",
            modifier = Modifier
                .padding(top = 5.dp)
        )
        Text(
            text = day,
            modifier = Modifier
                .padding(top = 5.dp)
                .clickable { action() }
        )
        // Buat button tambah
        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor = Color.White
            ),
            // Kecilin buttonnya
            modifier = Modifier.height(30.dp),
            shape = CutCornerShape(percent = 25)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White
            )
        }
    }
}
//
//// Function content jadwal
//@Composable
//fun ContentJadwal(pukul: String, jadwal: String) {
//    Row(
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//    ) {
//        Text(
//            text = pukul,
//            modifier = Modifier
//                .padding(top = 5.dp)
//        )
//        Spacer(modifier = Modifier.width(10.dp))
//        Text(
//            text = jadwal,
//            modifier = Modifier
//                .padding(top = 5.dp)
//        )
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    UASMuhammadAlfinKhaerudinTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            //Greeting("Android")
//
//            /*// Take day from system
//            val day = java.time.LocalDate.now().dayOfWeek.toString()
//            // Convert day to indonesian language
//            val day_indo_version = when (day) {
//                "MONDAY" -> "Senin"
//                "TUESDAY" -> "Selasa"
//                "WEDNESDAY" -> "Rabu"
//                "THURSDAY" -> "Kamis"
//                "FRIDAY" -> "Jumat"
//                "SATURDAY" -> "Sabtu"
//                "SUNDAY" -> "Minggu"
//                else -> "Unknown"
//            }
//            TopAppBar(name_app = "JAPEL", day = day_indo_version)*/
//
//            /*// Content jadwal
//            ContentJadwal(pukul = "08.00 - 10.00", jadwal = "Pemrograman Mobile")*/
//
//            Main()
//        }
//    }
//}