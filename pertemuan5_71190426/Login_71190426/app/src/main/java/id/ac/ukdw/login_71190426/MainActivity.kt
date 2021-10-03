package id.ac.ukdw.login_71190426

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val edtUsername = findViewById<EditText>(R.id.isian_username)
        val edtPassword = findViewById<EditText>(R.id.isian_password)
        val button_login = findViewById<Button>(R.id.button_login)
        val textVerifikasi = findViewById<TextView>(R.id.textView_verifikasi)

        edtPassword.addTextChangedListener {
            textVerifikasi.text = ""
        }

        button_login.setOnClickListener {
            if(edtPassword.text.toString() == "1234"){
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("username", edtUsername.text.toString())
                startActivity(intent)
            }else{
                textVerifikasi.text = "Password yang anda masukan salah"

            }



        }



    }
}