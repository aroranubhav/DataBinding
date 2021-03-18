package com.maxi.databinding.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

enum class Popularity {
    NORMAL,
    POPULAR,
    STAR
}

class MainViewModel : ViewModel() {

    private val _name = MutableLiveData("Anubhav")
    private val _lastName = MutableLiveData("Arora")
    private val _likes = MutableLiveData(0)

    val name: LiveData<String> = _name
    val lastName: LiveData<String> = _lastName
    val likes: LiveData<Int> = _likes

    fun likes() {
        _likes.value = (_likes.value ?: 0) + 1
    }

    val popularity: LiveData<Popularity> = Transformations.map(_likes) {
        when {
            it > 10 -> Popularity.STAR
            it > 5 -> Popularity.POPULAR
            else -> Popularity.NORMAL
        }
    }
}