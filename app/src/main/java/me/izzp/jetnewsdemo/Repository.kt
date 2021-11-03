package me.izzp.jetnewsdemo

import kotlinx.coroutines.delay
import me.izzp.jetnewsdemo.data.PostsFeed
import me.izzp.jetnewsdemo.data.posts
import kotlin.random.Random

object Repository {

    private val random = Random(System.currentTimeMillis())

    suspend fun getPostsFeed(): PostsFeed? {
        delay(2000)
        return if (random.nextFloat() < 0.2) {
            null
        } else {
            posts
        }
    }

    suspend fun getPost(id: String) = posts.allPosts.find { it.id == id }

}