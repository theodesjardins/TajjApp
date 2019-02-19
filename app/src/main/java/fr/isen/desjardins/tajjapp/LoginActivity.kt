package fr.isen.desjardins.tajjapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    companion object {
        val USER_PREFS = "users_prefs"
    }

    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //button
        pref = getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)
        autolog()
        submitButton.setOnClickListener {
            dologin()

        }
    }

    fun dologin() {
        if (canlog(emailField.text.toString(), passwordField.text.toString())) {//si Email tapez et mdp tapez sont correct

            saveusers(emailField.text.toString(), passwordField.text.toString())
            val intent = Intent(this, HomeActivity::class.java)

            startActivity(intent)

        } else {
            Toast.makeText(this, "erreur d'identifiant ou de mdp", Toast.LENGTH_LONG).show()
        }
    }

    fun autolog() {
        val email = pref.getString("identifiant", "")
        val password = pref.getString("password", "")

        if (canlog(email, password)) {
            emailField.setText(email)
            passwordField.setText(password)
            dologin()
        }
    }

    fun saveusers(email: String, password: String) {
        var editor = pref.edit()
        editor.putString("identifiant", email)
        editor.putString("password", password)
        editor.apply()
    }

    fun canlog(email: String, password: String): Boolean { //Methode
        return email == "admin" && password == "123"       //condition
    }

}