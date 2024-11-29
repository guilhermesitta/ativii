package com.example.consumodeapiapp.repository

import com.example.consumodeapiapp.network.ApiService
import com.example.consumodeapiapp.network.RetrofitClient

class PostRepository {
    private val apiService: ApiService = RetrofitClient.apiService

    fun getPosts() = apiService.getPosts()

    fun createPost(post: com.example.consumodeapiapp.network.Post) = apiService.createPost(post)
}
