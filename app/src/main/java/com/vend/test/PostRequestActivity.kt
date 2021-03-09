package com.vend.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.vend.test.databinding.ActivityPostRequestBinding
import com.vend.test.model.PostsData
import com.vend.test.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRequestActivity : AppCompatActivity() {

    private var binding: ActivityPostRequestBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_request)
        postDataToServer()
    }

    private fun postDataToServer(){
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                RetrofitInstance.getInstance()!!.postData(PostsData("hi my name is talha","meraa naam",123)).enqueue(object : Callback<Any> {

                        override fun onFailure(call: Call<Any>, t: Throwable) {
                            Toast.makeText(this@PostRequestActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(
                            call: Call<Any>,
                            response: Response<Any>
                        ) {
                            if (response.isSuccessful) {

                                Toast.makeText(this@PostRequestActivity, "${response.body()}", Toast.LENGTH_SHORT).show()
                            }


                        }
                    })
            }
        }
    }
}