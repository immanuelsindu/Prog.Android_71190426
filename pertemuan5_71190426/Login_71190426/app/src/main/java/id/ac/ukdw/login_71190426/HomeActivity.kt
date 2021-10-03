package id.ac.ukdw.login_71190426

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val username = intent.getStringExtra("username")
        val ucapan = findViewById<TextView>(R.id.txtView_ucapan)
        ucapan.text = "Selamat Datang ${username}"

    }



}