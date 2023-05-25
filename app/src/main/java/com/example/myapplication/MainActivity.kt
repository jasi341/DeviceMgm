

package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.Utilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Utilities.background
                ) {

                    val mainViewModel = viewModel<MainViewModel>()

                    FullView(mainViewModel)
                }
            }
        }
    }
}







fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}



@Composable
fun BottomSheetRemove3(viewModel: MainViewModel) {
    val items = remember { mutableStateListOf<Model>() }
    items.addAll(listDevice)

    val coroutineScope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items) { item ->
                Text(text = item.device_Type, color = Color.White)
            }
        }

        Row(modifier = Modifier.weight(1f)) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    Log.d("TAG12", "BottomSheetRemove: ${viewModel.type.value}")
                    viewModel.removeType("All", 0)
                    if (viewModel.type.value == "All") {
                        items.clear()
                    }
                    Log.d("TAG123", "BottomSheetRemove: ${viewModel.type.value}")
                }
            ) {
                Text(text = "state.value")
            }

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    Log.d("TAG1234", "BottomSheetRemove: ${viewModel.type.value}")
                    val currentType = viewModel.type.value
                    val currentIndex = viewModel.index.value
                    coroutineScope.launch {
                        viewModel.removeType("Single", 0)
                        if (currentType == "Single") {
                            items.removeAt(currentIndex)
                        }
                        Log.d("TAG12345", "BottomSheetRemove: ${viewModel.type.value}")
                    }
                }
            ) {
                Text(text = "state.value")
            }
        }
    }
}


@Composable
fun BottomSheetRemove4(viewModel: MainViewModel) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.items) { item ->
                Text(text = item.device_Type, color = Color.White)
            }
        }

        Row(modifier = Modifier.weight(1f)) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    Log.d("TAG12", "BottomSheetRemove: ${viewModel.type.value}")
                    viewModel.removeType("All", 0)
                    Log.d("TAG123", "BottomSheetRemove: ${viewModel.type.value}")
                }
            ) {
                Text(text = "state.value")
            }

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    Log.d("TAG1234", "BottomSheetRemove: ${viewModel.type.value}")
                    val currentType = viewModel.type.value
                    val currentIndex = viewModel.index.value
                    coroutineScope.launch {
                        viewModel.removeType("Single", currentIndex)
                        Log.d("TAG12345", "BottomSheetRemove: ${viewModel.type.value}")
                    }
                }
            ) {
                Text(text = "state.value")
            }
        }
    }
}











