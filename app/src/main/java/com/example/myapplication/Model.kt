package com.example.myapplication




data class Model(
    val image:Int,
    val device_Type : String,
    val date_Added : String,
    val last_Watched : String,
    val location: String,
    var isCurrentDevice:Boolean
)

val listDevice = listOf(
    Model(R.drawable.baseline_phone_iphone_24,"Mobile","2021-06-01","2021-06-01","Rigewood,United States",true),
    Model(R.drawable.outline_laptop_24, "Web Browser", "10/4/22", "11/3/22", "Rigewood,United States",false),
    Model(R.drawable.baseline_phone_iphone_24, "Mobile", "10/4/22", "11/3/22", "Rigewood,United States",false)
)


