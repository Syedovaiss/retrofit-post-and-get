package com.vend.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vend.test.adapter.PostAdapter
import com.vend.test.apiservice.MyAPIService
import com.vend.test.databinding.ActivityMainBinding
import com.vend.test.model.Post
import com.vend.test.model.PostItem
import com.vend.test.network.RetrofitInstance
import com.vend.test.repo.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() , PostAdapter.OnPostClickListener{

    private var binding: ActivityMainBinding? = null
    private lateinit var postAdapter:PostAdapter

    private lateinit var postRepository: PostRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getPostsFromAPI()
        postAdapter = PostAdapter(this)
        postRepository = PostRepository(this)

    }

    private fun getAllPosts(){
      val posts =   postRepository.getAllPosts()

        setupRecyclerView(post = posts)
    }

    private fun getPostsFromAPI(){


      lifecycleScope.launch {
          withContext(Dispatchers.IO) {
              RetrofitInstance.getInstance()!!.getPosts().enqueue(object : Callback<Post> {

                  override fun onFailure(call: Call<Post>, t: Throwable) {

                      Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                  }

                  override fun onResponse(
                      call: Call<Post>,
                      response: Response<Post>
                  ) {
                      response.body()?.let { posts ->
                          lifecycleScope.launch {
                              withContext(Dispatchers.IO){
                                  postRepository.insertPosts(posts)
                              }
                          }

                          setupRecyclerView(posts)
                      }

                  }
              })
          }
      }
    }

    private fun setupRecyclerView(post:ArrayList<PostItem>) {

        binding?.recyclerView?.also {
            postAdapter.addPosts(post)
            it.adapter = postAdapter
            it.layoutManager = LinearLayoutManager(this)

        }
    }

    override fun onPostClicked(post: PostItem) {

        Intent(this@MainActivity,PostDetailActivity::class.java).also {
            it.putExtra("post",post)
            startActivity(it)
        }
    }
}