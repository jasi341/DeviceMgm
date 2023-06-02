package com.example.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.utils.Utilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FullView(viewModel: MainViewModel) {
    val height = LocalConfiguration.current.screenHeightDp.dp




    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 10.dp)
    ) {
        CurrentDevice()
        Box(modifier = Modifier.height(height).background(Color.Transparent)) {
            OtherDevices(
                header = "Not seeing all of your devices? Sign out and sign back in on that device to see it below.",
                viewModel
            )
        }

    }


}


@Composable
fun CurrentDevice(device: Model = Model(R.drawable.desktop, "Web Browser", "10/4/22", "2023-05-18T12:20:48.950Z", "Rigewood,United States",true)) {

    Card(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Utilities.background2
        )
    ) {
        DeviceRow(data = device, isCurrentDevice = device.isCurrentDevice)


    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherDevices(header: String, viewModel: MainViewModel) {

    val coroutineScope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(

    )
    val bottomSheetState1 = viewModel.bottomSheetState.collectAsState()


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
                    onClick = {
                        viewModel.updateBottomSheetState(true)
                        viewModel.updateIndex("All",-1)
                    },
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 15.dp),
                userScrollEnabled = false
            ) {

                itemsIndexed(viewModel.items) { index, data ->
                    if(!data.isCurrentDevice){
                        DeviceRow(data, coroutineScope, viewModel, index,false)
                        Divider(
                            color = Utilities.background3,
                            modifier = Modifier.padding(horizontal = 15.dp)
                        )
                    }


                }

            }


            if (bottomSheetState1.value) {
                val typeValue = viewModel.type.collectAsState()

                ModalBottomSheet(
                    onDismissRequest = { viewModel.updateBottomSheetState(false) },
                    sheetState = bottomSheetState,
                    containerColor = Utilities.background2,
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {

                        val textValue = if (typeValue.value == "Single") "Remove Device" else "Remove All Devices"
                        val waringText =  if (typeValue.value == "Single")
                            "Are you sure you want to remove this device? It will be permanently delete from your device management."
                        else
                            "Are you sure you want to remove all devices? It will be permanently delete from your device management."

                        Text(
                            text = textValue ,
                            modifier = Modifier
                                .padding(bottom = 5.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontFamily = Utilities.font,
                            fontSize = 24.sp,
                            color = Color.White,
                        )

                        Text(
                            text =waringText,
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
                                if (typeValue.value == "All") {
                                    viewModel.removeItem(type ="All",0)
                                    viewModel.updateBottomSheetState(false)
                                } else {
                                    viewModel.removeItem("Single", viewModel.index.value)
                                    viewModel.updateBottomSheetState(false)
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
                                    viewModel.updateBottomSheetState(false)
                                },
                            textDecoration = TextDecoration.Underline
                        )

                    }
                }
            }
        }
    }
}

@Composable
private fun DeviceRow(
    data: Model,
    coroutineScope: CoroutineScope?= null,
    viewModel: MainViewModel?= null,
    index: Int?= null,
    isCurrentDevice :Boolean
) {
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
        Spacer(modifier = Modifier.width(2.dp))

        Column {
            val CurrentDate = data.updateDate
            val updateDate = Utilities.convertTimestampToDate(CurrentDate)

            Text(
                text = data.platform,
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
                text = "Last Watched :$updateDate",
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


        if (!isCurrentDevice) {
            IconButton(
                onClick = {
                    coroutineScope?.launch {
                        viewModel?.updateBottomSheetState(true)
                        viewModel?.updateIndex("Single", index!!)

                    }
                }
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = null,
                )
            }

        }
    }
}
