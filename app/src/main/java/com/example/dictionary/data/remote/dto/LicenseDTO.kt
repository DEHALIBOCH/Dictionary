package com.example.dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LicenseDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)