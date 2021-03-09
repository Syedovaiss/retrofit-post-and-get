package com.vend.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.vend.test.databinding.ActivityPostDetailBinding
import com.vend.test.model.PostItem

class PostDetailActivity : AppCompatActivity() {

    private  var binding:ActivityPostDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_post_detail)
        setPosts()
        routeToPostRequest()
    }

    private fun setPosts(){
        val posts = intent?.extras?.get("post") as PostItem
        binding?.posts = posts

    }

    private fun routeToPostRequest(){
        binding?.nextButton?.setOnClickListener {
            Intent(this,PostRequestActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}