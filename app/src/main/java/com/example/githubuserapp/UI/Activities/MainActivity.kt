package com.example.githubuserapp.UI.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapp.Model.UserGithub
import com.example.githubuserapp.R
import com.example.githubuserapp.UI.Adapters.ListUserAdapter

class MainActivity : AppCompatActivity(),  SearchView.OnQueryTextListener {
    private  lateinit var githubUsers: RecyclerView
    private val list = ArrayList<UserGithub>()
    private lateinit var adapter: ListUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        githubUsers = findViewById(R.id.githubUser)
        githubUsers.setHasFixedSize(true)

        val searchView: SearchView = findViewById(R.id.githubSearch)


        list.addAll(listUser)

        showRecyclerList()

    }


    private val listUser: ArrayList<UserGithub>
        @SuppressLint("Recycle")
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataPhoto = resources.obtainTypedArray(R.array.avatar)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataUrl = resources.getStringArray(R.array.url)
            val listUser = ArrayList<UserGithub>()
            for (i in dataName.indices) {
                val user = UserGithub(dataName[i], dataUsername[i], dataCompany[i], dataPhoto.getResourceId(i, -1), dataLocation[i], dataRepository[i], dataFollowers[i], dataFollowing[i], dataUrl[i])
                listUser.add(user)
            }
            return listUser
        }
    private fun  showRecyclerList(){

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            githubUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            githubUsers.layoutManager = LinearLayoutManager(this)
        }
        githubUsers.layoutManager = LinearLayoutManager(this)

        val listUserAdapter = ListUserAdapter(list)
        githubUsers.adapter = listUserAdapter


        listUserAdapter.setOnItemClickCallback(object: ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserGithub) {
                onClickSelectUser(data)
            }
        })

    }

    private  fun onClickSelectUser(user: UserGithub){
        val moveIntentToParcelable = Intent(this@MainActivity, DetailUserActivity::class.java)
        moveIntentToParcelable.putExtra(DetailUserActivity.EXTRA_USER, user)
        startActivity(moveIntentToParcelable)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null) {
            adapter.filter(newText)
        }
        return false
    }


}