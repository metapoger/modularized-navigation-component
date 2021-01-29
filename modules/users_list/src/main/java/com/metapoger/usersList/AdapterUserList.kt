package com.metapoger.usersList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.metapoger.core.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class AdapterUserList(
    private val data: ArrayList<User>,
    private val onClick: (View, User, Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view).apply {
            itemView.setOnClickListener { v ->
                val adapterPosition = this.adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onClick(v, data[adapterPosition], adapterPosition)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(data[position])
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: ImageView = itemView.avatarImageView
        private val nameTextView = itemView.nameTextView

        fun bind(user: User?) {
            if (user != null) {
                Glide.with(itemView)
                    .load(user.avatar)
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .into(avatarImageView)

                nameTextView.text = user.name
            }

            avatarImageView.transitionName = "transition:$adapterPosition"
            itemView.transitionName = "transition:$adapterPosition-123"
        }
    }

    override fun getItemCount() = data.size
}