package fr.isen.desjardins.tajjapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.database.*
import fr.isen.desjardins.tajjapp.Adapters.StatusAdapter
import fr.isen.desjardins.tajjapp.Model.RandomStatusResult
import fr.isen.desjardins.tajjapp.models.Member
import fr.isen.desjardins.tajjapp.models.Post
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val requestURL = "https://randomuser.me/api/?results=20"


    // connexion firebase
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("posts")

        /*val queue = Volley.newRequestQueue( this)

        val stringReq = StringRequest (Request.Method.GET, requestURL, Response.Listener{
            Log.d("request",it)
            val gson = Gson()
            val result = gson.fromJson<RandomStatusResult>(it, RandomStatusResult::class.java)
            val adapter = StatusAdapter(users = result.results)*/


        //myRef.child(myRef.push().key.toString()).setValue(Post("papa", "maman", myRef.push().key, "Bellydech"))


        val messageListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    var postsList: ArrayList<Post?> = ArrayList()
                    for(ds in dataSnapshot.children)
                    {
                        postsList.add(ds.getValue(Post::class.java))
                    }

                    myList.adapter = StatusAdapter(postsList)
                    Toast.makeText(this@HomeActivity,"Hello", Toast.LENGTH_LONG).show()

                }

            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }

        myRef.addValueEventListener(messageListener)

       /* }, Response.ErrorListener {

        })

        queue.add(stringReq)*/

        /*
        val users = ArrayList<UserModel>()

        users.add(UserModel())
        users.add(UserModel())
        users.add(UserModel())
        users.add(UserModel())
        users.add(UserModel())
        users.add(UserModel())
        users.add(UserModel())
        users.add(UserModel())
        users.add(UserModel())
        users.add(UserModel())
        users.add(UserModel())
        users.add(UserModel())
        users.add(UserModel())

        val adapter = UsersAdapter(users = users)

         myList.adapter = adapter
         */
        myList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false)

    }
}
