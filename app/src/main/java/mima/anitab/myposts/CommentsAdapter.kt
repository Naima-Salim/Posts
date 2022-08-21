package mima.anitab.myposts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mima.anitab.myposts.databinding.CommentsListsBinding

class CommentsAdapter (var commentsList: List<Comment>):RecyclerView.Adapter<CommentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var binding=CommentsListsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var currentComments=commentsList.get(position)
        with(holder.binding){
            tvPostId.text=currentComments.postId.toString()
            tvID.text=currentComments.id.toString()
            tvEmail.text=currentComments.email
            tvName.text=currentComments.name
            tvbody.text=currentComments.body

        }
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }
}
class CommentViewHolder(var binding: CommentsListsBinding):RecyclerView.ViewHolder(binding.root){

}