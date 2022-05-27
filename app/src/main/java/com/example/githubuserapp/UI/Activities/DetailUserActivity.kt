package com.example.githubuserapp.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.githubuserapp.Model.UserGithub
import com.example.githubuserapp.R


class DetailUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val dataUsers = intent.getParcelableExtra<UserGithub>(EXTRA_USER)

        val actionBarDetail = supportActionBar
        actionBarDetail!!.title = "Detail User"
        actionBarDetail.setDisplayHomeAsUpEnabled(true)


        val githubImage: ImageView = findViewById(R.id.githubProfile)
        val githubName: TextView = findViewById(R.id.githubName)
        val githubUserName: TextView = findViewById(R.id.githubUserName)
        val githubCompany: TextView = findViewById(R.id.githubCompany)
        val githubLocation: TextView = findViewById(R.id.githubLocation)
        val githubFollower: TextView = findViewById(R.id.githubFollowerValue)
        val githubFollowing: TextView = findViewById(R.id.githubFollowingValue)
        val githubRepository: TextView = findViewById(R.id.githubRepositoryValue)
        val githubUrl: TextView = findViewById(R.id.githubUrl)
        val githubShare: Button = findViewById(R.id.githubShare)
        val githubAddFollow: Button = findViewById(R.id.add_follow)
        if (dataUsers != null){
            githubName.text = dataUsers.name
            githubCompany.text = dataUsers.company
            githubLocation.text = dataUsers.location
            githubFollower.text = dataUsers.followers
            githubFollowing.text = dataUsers.following
            githubUserName.text = dataUsers.username
            githubRepository.text = dataUsers.repository
            githubUrl.text = dataUsers.url
            githubImage.setImageResource(dataUsers.photo)

            githubShare.setOnClickListener {
                Toast.makeText(this@DetailUserActivity , "Share " + githubName.text, Toast.LENGTH_SHORT).show()
            }
            githubAddFollow.setOnClickListener {
                Toast.makeText(this@DetailUserActivity, "Following " + githubName.text, Toast.LENGTH_SHORT).show()
            }

        }
    }
    companion object {
        const val EXTRA_USER = "extra_user"
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }
}