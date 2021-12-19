package id.ac.ukdw.ti.tas_71190426

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = findViewById<EditText>(R.id.edtEmail).text.toString().trim()
            val password = findViewById<EditText>(R.id.edtPassword).text.toString().trim()

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener (
                    OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            Toast.makeText(
                                this@MainActivity,
                                "Registrasi Berhasil",
                                Toast.LENGTH_LONG
                            )
                                .show()

                            val intent =
                                Intent(this@MainActivity, home::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", firebaseUser.uid)
                            intent.putExtra("email_id", email)

                            DataGlobal.isiEmail(email)

                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@MainActivity, "Registrasi Gagal", Toast.LENGTH_LONG)
                                .show()
                        }
                    })

        }


    }

}
