package com.vend.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vend.test.databinding.ItemRowsBinding
import com.vend.test.model.PostItem

class PostAdapter(private  var onItemClickListener:OnPostClickListener) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    private var posts = ArrayList<PostItem>()

    fun addPosts(list: ArrayList<PostItem>) {
        this.posts = list
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val items = posts[position]
        holder.bind(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view: ItemRowsBinding =
            ItemRowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(view)

    }

    inner class PostViewHolder(var itemRowsBinding: ItemRowsBinding) :
        RecyclerView.ViewHolder(itemRowsBinding.root) {

        fun bind(item: PostItem) {
            itemRowsBinding.posts = item
            itemRowsBinding.postTitle.setOnClickListener {
                onItemClickListener.onPostClicked(item)
            }

        }
    }

    interface OnPostClickListener {
        fun onPostClicked(post: PostItem)
    }
}