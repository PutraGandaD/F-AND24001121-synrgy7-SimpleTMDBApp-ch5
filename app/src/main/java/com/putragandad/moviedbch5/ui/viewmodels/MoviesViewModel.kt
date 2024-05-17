package com.putragandad.moviedbch5.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.putragandad.moviedbch5.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import java.lang.IllegalArgumentException

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {
    fun getMovieNowPlaying() = liveData(Dispatchers.IO) {
        emit(repository.getMovieNowPlaying())
    }
}

