package id.ac.ukdw.ti.tas_71190426

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class UpdateFilm: AppCompatActivity() {
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_film)

        val edtJudulFilm = findViewById<EditText>(R.id.update_JudulFilm)
        val edtRating = findViewById<EditText>(R.id.update_RatingFilm)
        val edtTanggalRilis = findViewById<EditText>(R.id.update_TanggalRilis)
        val edtKategori = findViewById<EditText>(R.id.update_Kategori)

        val btnUpdate = findViewById<Button>(R.id.update_btnUpdate)
        val btnBatal = findViewById<Button>(R.id.update_btnBatal)

        val iJudul = getIntent().getStringExtra("judulFilm")
        val iRating = getIntent().getStringExtra("rating")
        val iTanggalRilis = getIntent().getStringExtra("tanggalRilis")
        val iKategori = getIntent().getStringExtra("kategori")

//        val judul2 = iJudul
        edtJudulFilm.setText(iJudul)
        edtRating.setText(iRating)
        edtTanggalRilis.setText(iTanggalRilis)
        edtKategori.setText(iKategori)

        firestore = FirebaseFirestore.getInstance()

        btnUpdate.setOnClickListener {
            if (edtJudulFilm.text.toString() != "" && edtRating.text.toString() != "" && edtTanggalRilis.text.toString() != "" && edtKategori.text.toString() != ""){
                Toast.makeText(this,DataGlobal.getJudul(),Toast.LENGTH_LONG).show()


                firestore?.collection("Film")?.whereEqualTo("judulFilm",DataGlobal.getJudul())?.get()
                    ?.addOnSuccessListener { hasil->
                        for(i in hasil){
                            firestore!!.collection("Film").document(i.id)
                                .update("judulFilm", edtJudulFilm.text.toString(),"rating",
                                    edtRating.text.toString(),"tanggalRilis",edtTanggalRilis.text.toString(),"kategori",edtKategori.text.toString())
                        }
                        Toast.makeText(this,"Data berhasil diupdate", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,home::class.java))
                    }
        }else{
                Toast.makeText(this,"Pastikan semua kolom sudah terisi",Toast.LENGTH_LONG).show()

            }

        }

        btnBatal.setOnClickListener {
            startActivity(Intent(this,home::class.java))
        }

    }
}