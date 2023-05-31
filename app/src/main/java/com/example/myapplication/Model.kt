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
    Model(R.drawable.phone,"Mobile","2021-06-01","2023-05-18T12:20:48.950Z","Rigewood,United States",true),
    Model(R.drawable.desktop, "Web Browser", "10/4/22", "2023-05-18T12:20:48.950Z", "Rigewood,United States",false),
    Model(R.drawable.tablet, "Tablet", "10/4/22", "2023-05-18T12:20:48.950Z", "Rigewood,United States",false),
    Model(R.drawable.phone, "Mobile", "10/4/22", "2023-05-18T12:20:48.950Z", "Rigewood,United States",false)
)


