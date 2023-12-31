package com.example.dictionary.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionary.domain.use_case.GetWordInfo
import com.example.dictionary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo
) : ViewModel() {

    private val _wordInfoState = MutableLiveData<WordInfoState>(WordInfoState())
    val wordInfoState: LiveData<WordInfoState>
        get() = _wordInfoState

    private val _uiEvent = MutableLiveData<UIEvent>()
    val uiEvent: LiveData<UIEvent>
        get() = _uiEvent

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfo(query).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _wordInfoState.value = wordInfoState.value?.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _wordInfoState.value = wordInfoState.value?.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _uiEvent.value = UIEvent.ShowSnackbar(result.message ?: "Unknown Error")
                    }

                    is Resource.Loading -> {
                        _wordInfoState.value = wordInfoState.value?.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}