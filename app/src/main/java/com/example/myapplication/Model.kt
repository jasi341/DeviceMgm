package com.example.myapplication




data class Model(
    val image:Int,
    val platform : String,
    val date_Added : String,
    val updateDate : String,
    val location: String,
    var isCurrentDevice:Boolean
)

val listDevice = listOf(
    Model(R.drawable.baseline_phone_iphone_24,"Mobile","2021-06-01","2023-05-18T12:20:48.950Z","Rigewood,United States",true),
    Model(R.drawable.outline_laptop_24, "Web Browser", "10/4/22", "2023-05-18T12:20:48.950Z", "Rigewood,United States",false),
    Model(R.drawable.baseline_phone_iphone_24, "Mobile", "10/4/22", "2023-05-18T12:20:48.950Z", "Rigewood,United States",false)
)


