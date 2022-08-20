package mima.anitab.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mima.anitab.myposts.databinding.ActivityComments2Binding
import mima.anitab.myposts.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    var postId = 0
    lateinit var binding:ActivityComments2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComments2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        fetchPostById()
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
}