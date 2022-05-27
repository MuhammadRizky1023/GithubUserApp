package com.example.githubuserapp.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuserapp.Model.UserGithub
import com.example.githubuserapp.R


class  ListUserAdapter(private val listUser: ArrayList<UserGithub>): RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val githubName: TextView = itemView.findViewById(R.id.githubName)
        val githubCompany: TextView = itemView.findViewById(R.id.githubCompany)
        val githubUserName: TextView = itemView.findViewById(R.id.githubUserName)
        val githubProfile: ImageView = itemView.findViewById(R.id.githubProfile)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
       val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        return  ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(name, company, username, photo)  = listUser[position]

        holder.githubName.text = name
        holder.githubCompany.text = company
        holder.githubUserName.text = username

        holder.githubProfile.setImageResource(photo)

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions())
            .into(holder.githubProfile)


        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int = listUser.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }



    interface OnItemClickCallback {
        fun onItemClicked(data: UserGithub)
    }

    fun filter(query: String) {
        notifyDataSetChanged()
    }


}