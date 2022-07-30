package mima.anitab.myposts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mima.anitab.myposts.databinding.PostsListItemBinding

class PostRvAdapter (var context: Context, var postList: List<Post>):RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var binding = PostsListItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currentPost = postList.get(position)
        with(holder.binding){
            tvUserId.text = currentPost.userId.toString()
            tvId.text = currentPost.id.toString()
            tvTitle.text = currentPost.title
            tvBody.text = currentPost.body
        }

    }

    override fun getItemCount(): Int {
        return postList.size

    }
}
class PostViewHolder(var binding: PostsListItemBinding):RecyclerView.ViewHolder(binding.root){}

