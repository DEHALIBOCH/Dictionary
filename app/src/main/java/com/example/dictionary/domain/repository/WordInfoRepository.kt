package com.example.dictionary.domain.repository

import com.example.dictionary.domain.model.WordInfo
import com.example.dictionary.util.Resource
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}