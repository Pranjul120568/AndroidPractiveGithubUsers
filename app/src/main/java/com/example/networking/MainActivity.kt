package com.example.networking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networking.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//Networking-Consuming some services or Api in mobile application
class MainActivity : AppCompatActivity() {
    private val adapter= UserAdapter()

    private val originalList= arrayListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter.onItemClick={
            val intent=Intent(this,UserActivity::class.java)
            intent.putExtra("ID",it)
            startActivity(intent)
        }
        binding.UserRv.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=this@MainActivity.adapter
        }
        GlobalScope.launch(Dispatchers.Main) {
            //Her we basically make a request to fetch all data
            val response= withContext(Dispatchers.IO){ Client.api.getUser() }
            if(response.isSuccessful){
                response.body()?.let {
                    //Here we add the data retrieved from the response request
                    originalList.addAll(it)
                    adapter.swapData(it)
                }
            }
        }
//        fun searchUsers(query:String){
//            GlobalScope.launch(Dispatchers.Main) {
//                val response= withContext(Dispatchers.IO){ Client.api.searchUser(query) }
//                if(response.isSuccessful){
//                    response.body()?.let {
//                        it.items?.let { it1 -> adapter.swapData(it1) }
//                    }
//                }
//            }
//        }
    }
}
      /* val okHttpClient=OkHttpClient()
        val request=Request.Builder()
            .url("https://api.github.com/users/Pranjul120568")
            .build()

        val gson=GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        GlobalScope.launch(Dispatchers.Main) {
            val response= withContext(Dispatchers.IO){okHttpClient.newCall(request).execute().body!!.string()}
            val user=gson.fromJson<User>(response,User::class.java)
            binding.tv1.text= user.name as CharSequence?
            binding.tv2.text=user.login
            Picasso.get().load(user.avatarUrl).into(binding.IV1)
        //binding.IV1.ima=image
        }
    }
}

The network call will be in async task as it is time taking process and
if try to perform that on main thread the app will crash and show not responding.
 */
//R.layout.activity_main