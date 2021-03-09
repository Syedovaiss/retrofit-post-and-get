package com.vend.test.model

import java.io.Serializable

data class PostItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) : Serializable

data class PostsData(
    val body: String,
    val title: String,
    val userId: Int
)