package com.example.myapplication

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.utils.Utilities

@Composable
fun FullView(viewModel: MainViewModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp)) {
        CurrentDevice()
        OtherDevices(header = "Not seeing all of your devices? Sign out and sign back in on that device to see it below.", viewModel )

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
                modifier = Modifier.padding(15.dp)
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
fun OtherDevices(header: String, viewModel: MainViewModel) {

    val coroutineScope = rememberCoroutineScope()
    val items = remember{ mutableStateListOf<Model>() }
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
                    onClick = { },
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
                itemsIndexed(viewModel.items) { i,data ->

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
                                text =data.device_Type,
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


                        IconButton(onClick = {
                            viewModel.removeType("Single",i)

                        })
                        {
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
        }
    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeviceItems(
    data: Model,
    removeDevice: Boolean,
    index: Int,
    items: List<Model>,
    viewModel: MainViewModel

) {

    val skipPartiallyExpanded by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    var remove by remember {
        mutableStateOf(removeDevice)
    }

}
