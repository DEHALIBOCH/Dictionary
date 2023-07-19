package com.example.dictionary.data.remote.dto


import com.example.dictionary.domain.model.Definition
import com.google.gson.annotations.SerializedName

data class DefinitionDTO(
    @SerializedName("antonyms")
    val antonyms: List<String>,
    @SerializedName("definition")
    val definition: String,
    @SerializedName("example")
    val example: String?,
    @SerializedName("synonyms")
    val synonyms: List<String>
) {
    fun toDefinition() = Definition(
        antonyms = antonyms,
        definition = definition,
        example = example,
        synonyms = synonyms
    )
}