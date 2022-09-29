package com.codetron.mvpgithubusers.features

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codetron.mvpgithubusers.R
import com.codetron.mvpgithubusers.model.User

typealias UserItemClickListener = (User) -> Unit

class ListUsersAdapter(
    private val itemClickListener: UserItemClickListener? = null
) : RecyclerView.Adapter<ListUsersAdapter.UserViewHolder>() {

    private val users = mutableListOf<User>()

    @SuppressLint("NotifyDataSetChanged")
    fun bindUsers(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_users, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageUser = view.findViewById<ImageView>(R.id.image_user)
        private val textUser = view.findViewById<TextView>(R.id.text_username)

        fun bind(model: User) {
            Glide.with(imageUser)
                .load(model.photoUrl)
                .into(imageUser)

            textUser.text = model.username

            itemView.setOnClickListener { itemClickListener?.invoke(model) }
        }

    }

}