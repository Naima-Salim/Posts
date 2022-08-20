package mima.anitab.myposts


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mima.anitab.myposts.databinding.PostsListItemBinding

class PostRvAdapter (var postList: List<Post>):RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var binding = PostsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currentPost = postList.get(position)
        val postBinding=holder.binding
            postBinding.tvUserId.text = currentPost.userId.toString()
            postBinding.tvId.text = currentPost.id.toString()
            postBinding.tvTitle.text = currentPost.title
            postBinding.tvBody.text = currentPost.body
        val context=holder.itemView.context
        holder.binding.cvPost.setOnClickListener {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra("POST_ID", currentPost.id)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return postList.size

    }
}
class PostViewHolder(var binding: PostsListItemBinding):RecyclerView.ViewHolder(binding.root){
}

