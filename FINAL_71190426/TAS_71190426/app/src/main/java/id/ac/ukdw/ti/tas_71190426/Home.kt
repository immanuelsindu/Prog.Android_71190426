package id.ac.ukdw.ti.tas_71190426

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class home: AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val email = intent.getStringExtra("email_id")

        val txtWelcome = findViewById<TextView>(R.id.txtWelcome)
        val btnTambahData = findViewById<Button>(R.id.btnTambahData)

        txtWelcome.text = DataGlobal.getEmail()

        btnTambahData.setOnClickListener {
            val intent = Intent(this, TambahData::class.java)
            intent.putExtra("email_id",email)
            startActivity(intent)

        }

        firestore = FirebaseFirestore.getInstance()
        var listFilm = arrayListOf<POJO_Film>()

        firestore?.collection("Film")?.get()?.
        addOnSuccessListener {hasil->
            for(i in hasil){
                listFilm.add(POJO_Film(i.data["judulFilm"] as String,i.data["rating"] as String,
                    i.data["tanggalRilis"] as String ,i.data["kategori"] as String ))
//                Toast.makeText(this,"Jumlah Data ${listFilm.size}", Toast.LENGTH_LONG).show()

                val rcyFilm = findViewById<RecyclerView>(R.id.rcyFilm)
                rcyFilm.layoutManager = LinearLayoutManager(this)
                val adapter = FilmAdapter(listFilm, this)
                rcyFilm.adapter = adapter

            }

        }

        val btnCariData = findViewById<Button>(R.id.btnCariData)


        btnCariData.setOnClickListener {
            val edtCariFilm = findViewById<EditText>(R.id.edtCariFilm)
            firestore?.collection("Film")?.whereEqualTo("judulFilm",edtCariFilm.text.toString())?.get()?.
            addOnSuccessListener { hasil->
                listFilm.clear()
                for(i in hasil){
                    listFilm.add(POJO_Film(i.data["judulFilm"] as String,i.data["rating"] as String,
                        i.data["tanggalRilis"] as String ,i.data["kategori"] as String ))
                }

                if(listFilm.size >=1){
                    val rcyFilm2 = findViewById<RecyclerView>(R.id.rcyFilm)
                    rcyFilm2.layoutManager = LinearLayoutManager(this)
                    val adapter2 = FilmAdapter(listFilm, this)
                    rcyFilm2.adapter = adapter2
                }else{
                    Toast.makeText(this,"Film tidak ditemukan", Toast.LENGTH_LONG).show()

                    firestore?.collection("Film")?.get()?.
                            addOnSuccessListener { hasil->
                                for(i in hasil){
                                    listFilm.add(POJO_Film(i.data["judulFilm"] as String,i.data["rating"] as String,
                                        i.data["tanggalRilis"] as String ,i.data["kategori"] as String ))
                                }
                                val rcyFilm3 = findViewById<RecyclerView>(R.id.rcyFilm)
                                rcyFilm3.layoutManager = LinearLayoutManager(this)
                                val adapter3 = FilmAdapter(listFilm, this)
                                rcyFilm3.adapter = adapter3

                            }

                }
//                Toast.makeText(this,"EdtCari = ${edtCariFilm}", Toast.LENGTH_LONG).show()
//                Toast.makeText(this,"Ukuran ListFilm = ${listFilm.size}", Toast.LENGTH_LONG).show()
            }?.addOnFailureListener{
                Toast.makeText(this,"Film tidak ditemukan", Toast.LENGTH_LONG).show()
            }
        }





    }






}