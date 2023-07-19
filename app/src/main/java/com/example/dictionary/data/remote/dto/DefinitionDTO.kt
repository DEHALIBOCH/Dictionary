package com.example.dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DefinitionDTO(
    @SerializedName("antonyms")
    val antonyms: List<String>,
    @SerializedName("definition")
    val definition: String,
    @SerializedName("example")
    val example: String,
    @SerializedName("synonyms")
    val synonyms: List<String>
)