package com.example.consumodeapiapp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

// Modelo de dados para simular a resposta da API
data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)

interface ApiService {
    @GET("/posts") // Endpoint para listagem de posts
    fun getPosts(): Call<List<Post>>

    @POST("/posts") // Endpoint para criar posts
    fun createPost(@Body post: Post): Call<Post>
}
