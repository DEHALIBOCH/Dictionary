package com.example.dictionary.data.remote.dto


import com.example.dictionary.data.local.entity.WordInfoEntity
import com.google.gson.annotations.SerializedName

data class WordInfoDTO(
    @SerializedName("license")
    val license: LicenseDTO,
    @SerializedName("meanings")
    val meanings: List<MeaningDTO>,
    @SerializedName("phonetic")
    val phonetic: String,
    @SerializedName("phonetics")
    val phonetics: List<PhoneticDTO>,
    @SerializedName("sourceUrls")
    val sourceUrls: List<String>,
    @SerializedName("word")
    val word: String
) {
    fun toWordInfoEntity() = WordInfoEntity(
        meanings = meanings.map { it.toMeaning() },
        phonetic = phonetic,
        word = word
    )
}