package com.vend.test.apiservice

import com.vend.test.model.Post
import com.vend.test.model.PostsData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyAPIService {

    @GET("posts")
    fun getPosts(): Call<Post>

    @POST("posts")
    fun postData(@Body posts: PostsData) : Call<Any>
}