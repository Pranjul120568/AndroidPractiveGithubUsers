package com.example.networking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.networking.databinding.ItemUserBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserActivity : AppCompatActivity() {
    var binding:ItemUserBinding ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ItemUserBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val id=intent.getStringExtra("ID")

        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { Client.api.getUserById("id") }
            if (response.isSuccessful) {
                response.body()?.let {
                    binding!!.tv1.text= it.name as CharSequence?
                    binding!!.tv2.text = it.login
                Picasso.get().load(it.avatarUrl).into(binding!!.IV1)
            }
        }
        }
    }
}