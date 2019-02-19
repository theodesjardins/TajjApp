package fr.isen.desjardins.tajjapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import fr.isen.desjardins.tajjapp.Adapters.StatusAdapter
import fr.isen.desjardins.tajjapp.Model.RandomStatusResult
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val requestURL = "https://randomuser.me/api/?results=20"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        val queue = Volley.newRequestQueue( this)

        val stringReq = StringRequest (Request.Method.GET, requestURL, Response.Listener{
            Log.d("request",it)
            val gson = Gson()
            val result = gson.fromJson<RandomStatusResult>(it, RandomStatusResult::class.java)
            val adapter = StatusAdapter(users = result.results)

            myList.adapter = adapter

        }, Response.ErrorListener {

        })

        queue.add(stringReq)

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
