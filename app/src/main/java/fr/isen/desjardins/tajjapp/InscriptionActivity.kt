package fr.isen.desjardins.tajjapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_inscription.*
import java.io.FileOutputStream

class InscriptionActivity : AppCompatActivity() {

    private val REQUEST_CODE = 11
    private val JSON_FILE = "json_file"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)

        signinSubmit.setOnClickListener{writeUser()}

        picSignin.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }


    }
    fun writeUser(){
        val name = textNom.text.toString()
        val firstName = textPrenom.text.toString()
        val nickname = textPseudo.text.toString()
        val password = textPsw.text.toString()
        val mail = textMail.text.toString()


        if(name.isNotEmpty() && firstName.isNotEmpty() && nickname.isNotEmpty() && password.isNotEmpty() && mail.isNotEmpty()){
            val jsonString = "{'name' : '$name', 'firstName' : '$firstName', 'nickname' : '$nickname', 'password' : '$password', 'mail' : '$mail'}"

            val fos: FileOutputStream
            fos = openFileOutput(JSON_FILE, Context.MODE_PRIVATE)
            fos.write(jsonString.toByteArray())
            fos.close()
            Toast.makeText(this, "Votre nom est $name et votre pseudo est $nickname", Toast.LENGTH_LONG).show()
            //Vider les fields
        }else{
            Toast.makeText(this, "Vous n'avez pas renseigner tout les champs", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val uri = it.data
                if (uri != null) {
                    val stream = contentResolver.openInputStream(uri)
                    val bitmap = BitmapFactory.decodeStream(stream)
                    picSignin.setImageBitmap(bitmap)
                }
            }
        }
    }
}
