package com.ntt.androidday16.model

data class Comment(
    var postId: Int = 0,
    var id: Int = 0,
    var name: String = "",
    var email: String = "",
    var body: String = ""
)
