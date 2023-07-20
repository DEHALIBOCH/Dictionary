package com.example.dictionary.presentation

import android.os.Bundle
import android.util.Log
import android.viewbinding.library.activity.viewBinding
import android.widget.SearchView.OnQueryTextListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: WordInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.uiEvent.observe(this, Observer { uiEvent ->
            when (uiEvent) {
                is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                    Snackbar.make(binding.root, uiEvent.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                viewModel.onSearch(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearch(newText ?: "")
                return false
            }
        })

        viewModel.wordInfoState.observe(this, Observer { state ->
            Log.d("STATE", state.wordInfoItems.joinToString())
            if (state.wordInfoItems.isNotEmpty()) {
                val strBuilder = StringBuilder()
                state.wordInfoItems.forEach { wordInfo ->
                    strBuilder
                        .append(wordInfo.word)
                        .append("\n")
                        .append(wordInfo.phonetic)
                        .append("\n")
                    wordInfo.meanings.forEach { meaning ->
                        meaning.definitions.forEachIndexed { index, definition ->
                            strBuilder.append("${index + 1}. ${definition.definition}\n")
                        }
                    }
                    strBuilder.append("\n").append("\n")
                }
                binding.wordInfoTextView.text = strBuilder.toString()
            }
        })
    }
}