package com.example.dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PhoneticDTO(
    @SerializedName("audio")
    val audio: String,
    @SerializedName("license")
    val license: LicenseDTO,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("text")
    val text: String
)