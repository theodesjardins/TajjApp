package fr.isen.desjardins.tajjapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import fr.isen.desjardins.tajjapp.models.Member
import kotlinx.android.synthetic.main.activity_member_sign_in.*

class MemberSignIn : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_sign_in)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("members")

        testButton.setOnClickListener {
            val newMember: Member = Member(myRef.push().key, "John", "Belly", "Bellydech", "adresse@mail.com")
            myRef.child(myRef.push().key.toString()).setValue(newMember)

        }

        val messageListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val member = dataSnapshot.getValue(Member::class.java)
                    var membersList: ArrayList<Member?> = ArrayList()
                   for(ds in dataSnapshot.children)
                   {
                       membersList.add(ds.getValue(Member::class.java))
                   }

                    Toast.makeText(this@MemberSignIn,"Helo", Toast.LENGTH_LONG).show()

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }

        myRef.addValueEventListener(messageListener)
    }




}
