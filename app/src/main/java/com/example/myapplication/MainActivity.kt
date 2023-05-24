package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.Utilities
import kotlinx.coroutines.delay
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
                    // MyLazyColumn()
                    FullView()
                }
            }
        }
    }
}



@Composable
fun MyLazyColumn() {
    // Define a mutable state list to hold your items
    val items = remember { mutableStateListOf<String>() }

//     Function to add an item to the list
    val itemCount = remember { mutableStateOf(0) }

    // Function to add an item to the list
    fun addItem() {
        val newItem = "Item ${itemCount.value + 1}"
        items.add(newItem)
        itemCount.value++
    }

    // Function to remove an item from the list
    fun removeAllItem(index: Int) {
        items.removeAll( { it == items[index] })
    }
    fun removeItem(index: Int) {
        items.removeAt(index)
    }

    Column {
        // Add a button to add an item
        Button(onClick = { addItem() }) {
            Text(text = "Add Item")
        }

        // Add a button to remove an item
        Button(onClick = { removeItem(0) }) {
            Text(text = "Remove Item")
        }
        Button(onClick = { removeAllItem(0) }) {
            Text(text = "Remove All Items")
        }

        // Add a LazyColumn
        LazyColumn {
            items(items) { item ->
                Text(text = item, color = Color.White)
            }
        }
    }


}

@Composable
fun CurrentDevice(device: Model = Model(R.drawable.outline_laptop_24, "Web Browser", "10/4/22", "11/3/22", "Rigewood,United States")) {

    Card(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Utilities.background2
        )
    ) {
        Column(modifier = Modifier.wrapContentHeight()) {
            Text(
                text = "Current device",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = Utilities.font,
                modifier =Modifier.padding(15.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 20.dp)) {
                Icon(
                    painter = painterResource(id = device.image),
                    contentDescription =null,
                    tint = Color.White,
                    modifier = Modifier
                        .height(58.dp)
                        .width(91.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))

                Column {

                    Text(
                        text = device.device_Type,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = Utilities.font,
                    )
                    Text(
                        text = "Date Added :${device.date_Added}",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = Utilities.font,
                    )
                    Text(
                        text = "Last Watched :${device.last_Watched}",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = Utilities.font,

                        )
                    Text(
                        text = device.location,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = Utilities.font,

                        )

                }

            }

        }

    }

}

@Composable
fun FullView() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp)) {
        CurrentDevice()
        OtherDevices(header = "Not seeing all of your devices? Sign out and sign back in on that device to see it below.")

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherDevices(header:String) {

    var removeAllDevices by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

    var removeDevice by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    val items = remember { mutableStateListOf<Model>() }
    items.addAll(listDevice)



    Column {
        Card(
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = Utilities.background2
            )
        ) {

            Column(modifier = Modifier.wrapContentHeight()) {
                Text(
                    text = "Other devices",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = Utilities.font,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    text = header,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontFamily = Utilities.font,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )

                Button(
                    onClick = { removeAllDevices = !removeAllDevices },
                    colors = ButtonDefaults.buttonColors(containerColor = Utilities.background3),

                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text(
                        color = Color.White,
                        text = "Remove All Devices",
                        fontSize = 14.sp,
                        fontFamily = Utilities.font
                    )
                }

                if (removeAllDevices) {
                    ModalBottomSheet(
                        onDismissRequest = { removeAllDevices = false },
                        sheetState = bottomSheetState,
                        containerColor = Utilities.background2
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {

                            Text(
                                text = "Remove all Devices?",
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontFamily = Utilities.font,
                                fontSize = 24.sp,
                                color = Color.White,
                            )

                            Text(
                                text = "Are your sure want to remove this device. It will be permanently delete from your device management.",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                textAlign = TextAlign.Center,
                                fontFamily = Utilities.font,
                                fontSize = 14.sp,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Button(
                                onClick = {
                                    scope.launch {
                                        delay(500)
                                        removeAllDevices = false
                                        items.removeAll(items)
                                    }

                                    scope.launch {
                                        delay(600)
                                        skipPartiallyExpanded = true
                                        toast(context, "Device Removed")
                                    }

                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),

                                modifier = Modifier
                                    .padding(horizontal = 15.dp, vertical = 5.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(0.dp)
                            ) {
                                Text(
                                    color = Color.White,
                                    text = "Confirm",
                                    fontSize = 14.sp,
                                    fontFamily = Utilities.font
                                )
                            }

                            Text(
                                color = Color.White,
                                text = "Cancel",
                                fontSize = 14.sp,
                                fontFamily = Utilities.font,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(bottom = 20.dp, top = 5.dp)
                                    .clickable {
                                        removeAllDevices = false
                                    },
                                textDecoration = TextDecoration.Underline
                            )

                        }
                    }
                }
            }
        }


            Card(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(
                    containerColor = Utilities.background2
                )
            ) {
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 15.dp)) {
                    items(items) { data ->

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 20.dp, top = 10.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = data.image),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .height(58.dp)
                                    .width(91.dp)
                            )
                            Spacer(modifier = Modifier.width(20.dp))

                            Column {

                                Text(
                                    text = data.device_Type,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontFamily = Utilities.font,
                                )
                                Text(
                                    text = "Date Added :${data.date_Added}",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontFamily = Utilities.font,
                                )
                                Text(
                                    text = "Last Watched :${data.last_Watched}",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontFamily = Utilities.font,

                                    )
                                Text(
                                    text = data.location,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontFamily = Utilities.font,

                                    )

                            }
                            Spacer(modifier = Modifier.weight(1f))


                            IconButton(onClick = { removeDevice = !removeDevice
                            }) {
                                Image(
                                    painter = painterResource(id = R.drawable.icon),
                                    contentDescription = null,

                                )

                            }

                        }

                        Divider(
                            color = Utilities.background3,
                            modifier = Modifier.padding(horizontal = 15.dp)
                        )


                    }
                }

                if (removeDevice) {
                    ModalBottomSheet(
                        onDismissRequest = { removeDevice = false },
                        sheetState = bottomSheetState,
                        containerColor = Utilities.background2
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {

                            Text(
                                text = "Remove Device?",
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontFamily = Utilities.font,
                                fontSize = 24.sp,
                                color = Color.White,
                            )

                            Text(
                                text = "Are your sure want to remove this device. It will be permanently delete from your device management.",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                textAlign = TextAlign.Center,
                                fontFamily = Utilities.font,
                                fontSize = 14.sp,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Button(
                                onClick = {
                                    scope.launch {
                                        delay(500)
                                        items.removeAt(0)
                                        removeDevice = false
                                    }

                                    scope.launch {
                                        delay(600)
                                        skipPartiallyExpanded = true
                                        toast(context, "Device Removed")
                                    }

                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),

                                modifier = Modifier
                                    .padding(horizontal = 15.dp, vertical = 5.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(0.dp)
                            ) {
                                Text(
                                    color = Color.White,
                                    text = "Confirm",
                                    fontSize = 14.sp,
                                    fontFamily = Utilities.font
                                )
                            }

                            Text(
                                color = Color.White,
                                text = "Cancel",
                                fontSize = 14.sp,
                                fontFamily = Utilities.font,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(bottom = 20.dp, top = 5.dp)
                                    .clickable {
                                        removeDevice = false
                                    },
                                textDecoration = TextDecoration.Underline
                            )

                        }
                    }
                }
            }
    }



}


fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}






