package com.example.networking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class UserAdapter:RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var data: List<User> = ArrayList()
    var onItemClick:((login:String)-> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
                )
    }
    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int)=holder.bind(data[position])
    override fun getItemCount(): Int =data.size
    fun swapData(data:List<User>){
        this.data=data
        notifyDataSetChanged()
    }
//Inner class is used to access variable declared out of scope
   inner class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(item:User)= with(itemView){

            val yaa=findViewById<TextView>(R.id.tv1)
            val yaay=findViewById<TextView>(R.id.tv2)
            val imgvw=findViewById<ImageView>(R.id.IV1)

                yaa.text=item.name as CharSequence?
                yaay.text=item.login
                Picasso.get().load(item.avatarUrl).into(imgvw)
            setOnClickListener{
                 onItemClick?.invoke(item.login!!)
            }
        }
    }
}