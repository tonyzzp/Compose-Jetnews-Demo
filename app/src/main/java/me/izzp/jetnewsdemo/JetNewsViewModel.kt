package me.izzp.jetnewsdemo

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.izzp.jetnewsdemo.data.Post
import me.izzp.jetnewsdemo.data.PostsFeed


class JetNewsViewModel : ViewModel() {

    var isLoadingFeed by mutableStateOf(false)
    var postsFeed by mutableStateOf<PostsFeed?>(null)
    var err by mutableStateOf(false)
    private val _favorites = MutableStateFlow(listOf<String>())
    val favorites = _favorites.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )

    init {
        refresh()
    }

    fun refresh() {
        isLoadingFeed = true
        err = false
        viewModelScope.launch {
            val rtn = Repository.getPostsFeed()
            isLoadingFeed = false
            if (rtn == null) {
                err = true
            } else {
                err = false
                postsFeed = rtn
            }
        }
    }

    fun toggleFavorite(key: String) {
        _favorites.update {
            val list = it.toMutableList()
            if (!list.remove(key)) {
                list.add(key)
            }
            list
        }
    }

    fun getPost(id: String): State<Post?> {
        val state = mutableStateOf<Post?>(null)
        viewModelScope.launch {
            state.value = Repository.getPost(id)
        }
        return state
    }

}