package mima.anitab.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import mima.anitab.myposts.databinding.ActivityComments2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity(Comment: Any?) : AppCompatActivity() {
    var postId = 0
    lateinit var binding:ActivityComments2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComments2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        obtainPostId()
        fetchPostById()
        fetchComments()
    }
    fun obtainPostId(){
        postId = intent.extras?.getInt("POST_ID")?:0 //?: is an elvis operator
    }
    fun fetchPostById(){
        val apiClient =  ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getPostById(postId)
        request.enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
             if (response.isSuccessful){
                 var post = response.body()
                 binding.tvPostTitle.text = post?.title
                 binding.tvPostPolly.text = post?.body
             }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
               Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun setupToolbar(){
        setSupportActionBar(binding.toolbarComments)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun fetchComments(){
        val apiClient =  ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getComments()
        request.enqueue(object :Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful){
                    var commentt = response.body()?: emptyList()
                    displayComment(commentt)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
        
        }

    fun displayComment(commentList: List<Comment>) {
        val commentsAdapter=CommentsAdapter(commentList)
        binding.rvComments.layoutManager=LinearLayoutManager(this)
        binding.rvComments.adapter=commentsAdapter

    }
}