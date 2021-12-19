package id.ac.ukdw.ti.tas_71190426

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class TambahData: AppCompatActivity() {
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah_data)

        firestore = FirebaseFirestore.getInstance()

        val email_id = intent.getStringExtra("email_id")
        val edtJudulFilm = findViewById<EditText>(R.id.edtJudulFilm)
        val edtRating = findViewById<EditText>(R.id.edtRatingFilm)
        val edtTanggalRilis = findViewById<EditText>(R.id.edtTanggalRilisFilm)
        val edtKategori = findViewById<EditText>(R.id.edtKategoriFilm)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnHome = findViewById<Button>(R.id.btnKembaliHome)

        btnSimpan.setOnClickListener {
            if(edtJudulFilm.text.toString() != "" && edtRating.text.toString() != "" && edtTanggalRilis.text.toString() != ""
                && edtKategori.text.toString() != "" ){

                val film = POJO_Film(edtJudulFilm.text.toString(),edtRating.text.toString(),edtTanggalRilis.text.toString(),edtKategori.text.toString())
                firestore?.collection("Film")?.add(film)

                Toast.makeText(this,"Film ${edtJudulFilm.text.toString()} berhasil ditambahkan", Toast.LENGTH_LONG).show()
                edtJudulFilm.setText("")
                edtRating.setText("")
                edtTanggalRilis.setText("")
                edtKategori.setText("")
            }else{
                Toast.makeText(this,"Simpan data gagal. Pastikan semua kolom sudah terisi",Toast.LENGTH_LONG).show()
            }
        }

        btnHome.setOnClickListener {
            val intent = Intent(this, home::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("email_id", email_id)
            startActivity(intent)
        }

    }
}
