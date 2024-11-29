package com.example.consumodeapiapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.consumodeapiapp.network.Post
import com.example.consumodeapiapp.repository.PostRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel : ViewModel() {
    private val repository = PostRepository()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status

    fun fetchPosts() {
        repository.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    _posts.postValue(response.body())
                } else {
                    _status.postValue("Erro ao carregar posts: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                _status.postValue("Erro: ${t.message}")
            }
        })
    }

    fun addPost(post: Post) {
        repository.createPost(post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    _status.postValue("Post criado com sucesso!")
                } else {
                    _status.postValue("Erro ao criar post: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                _status.postValue("Erro: ${t.message}")
            }
        })
    }
}
