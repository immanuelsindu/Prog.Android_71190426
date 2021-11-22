package id.ac.ukdw.ti.a71190426_firebase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()

        val edtNama = findViewById<EditText>(R.id.edtnama)
        val edtNIM = findViewById<EditText>(R.id.edtNIM)
        val edtIPK = findViewById<EditText>(R.id.edtIPK)
        val txtColumnNIM = findViewById<TextView>(R.id.txtColumnNim)
        val txtColumnNama = findViewById<TextView>(R.id.txtColumnNama)
        val txtColumnIpk = findViewById<TextView>(R.id.txtColumnIpk)

        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnCariData = findViewById<Button>(R.id.btnCariData)
        val btnIPKAsc = findViewById<Button>(R.id.btnIPKAsc)
        val btnIPKDesc = findViewById<Button>(R.id.btnIPKDesc)
        val btnNIMAsc = findViewById<Button>(R.id.btnNIMAsc)
        val btnNIMDesc = findViewById<Button>(R.id.btnNIMDesc)
        val btnNamaAsc = findViewById<Button>(R.id.btnNamaAsc)
        val btnNamaDesc = findViewById<Button>(R.id.btnNamaDesc)

        var listMahasiswa2 = arrayListOf<Mahasiswa>()


//        var listMahasiswa = arrayListOf<Mahasiswa>()

//        var rcyIsianKolomMahasiswa = findViewById<RecyclerView>(R.id.rcyIsianKolom)
//        rcyIsianKolomMahasiswa.layoutManager = LinearLayoutManager(this)




        btnSimpan.setOnClickListener{
            if(edtNama.text.toString() != "" && edtNIM.text.toString() != "" && edtIPK.text.toString() != ""){
                val mahasiswa = Mahasiswa(edtNama.text.toString(), edtNIM.text.toString(), edtIPK.text.toString())
                firestore?.collection("Mahasiswa")?.add(mahasiswa)

                Toast.makeText(this,"Data ${edtNama.text.toString()} berhasil ditambahkan",Toast.LENGTH_LONG).show()
                edtNama.setText("")
                edtNIM.setText("")
                edtIPK.setText("")
            }else{
                Toast.makeText(this,"Simpan data gagal. Pastikan semua kolom sudah terisi",Toast.LENGTH_LONG).show()
            }
        }

        btnCariData.setOnClickListener {
            if(edtNama.text.toString() != "" && edtNama.text.toString() != null){
                txtColumnNIM.setText("")
                txtColumnNama.setText("")
                txtColumnIpk.setText("")
                firestore?.collection("Mahasiswa")?.whereEqualTo("nama", edtNama.text.toString())?.get()?.
                        addOnSuccessListener {hasil->
                            for (d in hasil){
                                txtColumnNIM.setText("${d.data["nim"]}\n\n")
                                txtColumnNama.setText("${d.data["nama"]}\n\n")
                                txtColumnIpk.setText("${d.data["ipk"]}\n\n")

                            }
                        }
            }else if(edtNIM.text.toString() != "" && edtNIM.text.toString() != null){
                txtColumnNIM.setText("")
                txtColumnNama.setText("")
                txtColumnIpk.setText("")

                firestore?.collection("Mahasiswa")?.whereEqualTo("nim", edtNIM.text.toString())?.get()?.
                addOnSuccessListener {hasil->
                    for (d in hasil){
                        txtColumnNIM.setText("${d.data["nim"]}\n\n")
                        txtColumnNama.setText("${d.data["nama"]}\n\n")
                        txtColumnIpk.setText("${d.data["ipk"]}\n\n")
                    }
                }
            }else if(edtIPK.text.toString() != "" && edtIPK.text.toString() != null){
                txtColumnNIM.setText("")
                txtColumnNama.setText("")
                txtColumnIpk.setText("")

                firestore?.collection("Mahasiswa")?.whereEqualTo("ipk", edtIPK.text.toString())?.get()?.
                addOnSuccessListener {hasil->
                    for (d in hasil){
                        txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.data["nim"]}\n\n")
                        txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.data["nama"]}\n\n")
                        txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.data["ipk"]}\n\n")
                        listMahasiswa2.add(Mahasiswa(d.data["nama"] as String,d.data["nim"] as String , d.data["ipk"] as String ))


                    }
                    Toast.makeText(this,"Memproses Pencarian IPK",Toast.LENGTH_LONG).show()
                }?.addOnFailureListener{
//                    Toast.makeText(this,"Ini ada di Pencarian IPK",Toast.LENGTH_LONG).show()
                }
//                var adapter = MahasiswaAdapter(listMahasiswa)
//                rcyIsianKolomMahasiswa.adapter = adapter


            }else{
//                print(listMahasiswa)
//                if(listMahasiswa.size != 0){
//                     listMahasiswa.clear()
//                }

                txtColumnNIM.setText("")
                txtColumnNama.setText("")
                txtColumnIpk.setText("")
//
                firestore?.collection("Mahasiswa")?.get()?.
                addOnSuccessListener {hasil->
                    for (d in hasil){
                        txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.data["nim"]}\n\n")
                        txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.data["nama"]}\n\n")
                        txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.data["ipk"]}\n\n")
                        listMahasiswa2.add(Mahasiswa(d.data["nama"] as String,d.data["nim"] as String , d.data["ipk"] as String ))
                    }
                    Toast.makeText(this,"Memproses semua data",Toast.LENGTH_LONG).show()
                }
//                var adapter = MahasiswaAdapter(listMahasiswa)
//                rcyIsianKolomMahasiswa.adapter = adapter
            }
        }

        btnNamaAsc.setOnClickListener {
            if(listMahasiswa2.isEmpty()){
                firestore?.collection("Mahasiswa")?.get()?.
                addOnSuccessListener {hasil->
                    for (d in hasil){
//                        txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.data["nim"]}\n\n")
//                        txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.data["nama"]}\n\n")
//                        txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.data["ipk"]}\n\n")
                        listMahasiswa2.add(Mahasiswa(d.data["nama"] as String,d.data["nim"] as String , d.data["ipk"] as String ))
                    }
//                    Toast.makeText(this,"Memproses semua data",Toast.LENGTH_LONG).show()
                }
            }

            val sortedList = listMahasiswa2.sortedWith(compareBy({it.nama}))
            txtColumnNIM.setText("")
            txtColumnNama.setText("")
            txtColumnIpk.setText("")

            for (d: Mahasiswa in sortedList){
                txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.NIM}\n\n")
                txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.nama}\n\n")
                txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.IPK}\n\n")
            }
        }

        btnNamaDesc.setOnClickListener {
            if(listMahasiswa2.isEmpty()){
                firestore?.collection("Mahasiswa")?.get()?.
                addOnSuccessListener {hasil->
                    for (d in hasil){
//                        txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.data["nim"]}\n\n")
//                        txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.data["nama"]}\n\n")
//                        txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.data["ipk"]}\n\n")
                        listMahasiswa2.add(Mahasiswa(d.data["nama"] as String,d.data["nim"] as String , d.data["ipk"] as String ))
                    }
//                    Toast.makeText(this,"Memproses semua data",Toast.LENGTH_LONG).show()
                }
            }
            val sortedList = listMahasiswa2.sortedWith(compareByDescending { it.nama })
            txtColumnNIM.setText("")
            txtColumnNama.setText("")
            txtColumnIpk.setText("")

            for (d: Mahasiswa in sortedList){
                txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.NIM}\n\n")
                txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.nama}\n\n")
                txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.IPK}\n\n")
            }
        }

        btnIPKAsc.setOnClickListener {
            if(listMahasiswa2.isEmpty()){
                firestore?.collection("Mahasiswa")?.get()?.
                addOnSuccessListener {hasil->
                    for (d in hasil){
//                        txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.data["nim"]}\n\n")
//                        txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.data["nama"]}\n\n")
//                        txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.data["ipk"]}\n\n")
                        listMahasiswa2.add(Mahasiswa(d.data["nama"] as String,d.data["nim"] as String , d.data["ipk"] as String ))
                    }
//                    Toast.makeText(this,"Memproses semua data",Toast.LENGTH_LONG).show()
                }
            }

            val sortedList = listMahasiswa2.sortedWith(compareBy({it.IPK}))
            txtColumnNIM.setText("")
            txtColumnNama.setText("")
            txtColumnIpk.setText("")

            for (d: Mahasiswa in sortedList){
                txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.NIM}\n\n")
                txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.nama}\n\n")
                txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.IPK}\n\n")
            }
        }

        btnNIMAsc.setOnClickListener {
            if(listMahasiswa2.isEmpty()){
                firestore?.collection("Mahasiswa")?.get()?.
                addOnSuccessListener {hasil->
                    for (d in hasil){
//                        txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.data["nim"]}\n\n")
//                        txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.data["nama"]}\n\n")
//                        txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.data["ipk"]}\n\n")
                        listMahasiswa2.add(Mahasiswa(d.data["nama"] as String,d.data["nim"] as String , d.data["ipk"] as String ))
                    }
//                    Toast.makeText(this,"Memproses semua data",Toast.LENGTH_LONG).show()
                }
            }

            val sortedList = listMahasiswa2.sortedWith(compareBy({it.NIM}))
            txtColumnNIM.setText("")
            txtColumnNama.setText("")
            txtColumnIpk.setText("")

            for (d: Mahasiswa in sortedList){
                txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.NIM}\n\n")
                txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.nama}\n\n")
                txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.IPK}\n\n")
            }
        }

        btnNIMDesc.setOnClickListener {
            if(listMahasiswa2.isEmpty()){
                firestore?.collection("Mahasiswa")?.get()?.
                addOnSuccessListener {hasil->
                    for (d in hasil){
//                        txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.data["nim"]}\n\n")
//                        txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.data["nama"]}\n\n")
//                        txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.data["ipk"]}\n\n")
                        listMahasiswa2.add(Mahasiswa(d.data["nama"] as String,d.data["nim"] as String , d.data["ipk"] as String ))
                    }
//                    Toast.makeText(this,"Memproses semua data",Toast.LENGTH_LONG).show()
                }
            }
            val sortedList = listMahasiswa2.sortedWith(compareByDescending { it.NIM })
            txtColumnNIM.setText("")
            txtColumnNama.setText("")
            txtColumnIpk.setText("")

            for (d: Mahasiswa in sortedList){
                txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.NIM}\n\n")
                txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.nama}\n\n")
                txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.IPK}\n\n")
            }
        }

        btnIPKDesc.setOnClickListener {
            if(listMahasiswa2.isEmpty()){
                firestore?.collection("Mahasiswa")?.get()?.
                addOnSuccessListener {hasil->
                    for (d in hasil){
//                        txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.data["nim"]}\n\n")
//                        txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.data["nama"]}\n\n")
//                        txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.data["ipk"]}\n\n")
                        listMahasiswa2.add(Mahasiswa(d.data["nama"] as String,d.data["nim"] as String , d.data["ipk"] as String ))
                    }
//                    Toast.makeText(this,"Memproses semua data",Toast.LENGTH_LONG).show()
                }
            }
            val sortedList = listMahasiswa2.sortedWith(compareByDescending { it.IPK })
            txtColumnNIM.setText("")
            txtColumnNama.setText("")
            txtColumnIpk.setText("")

            for (d: Mahasiswa in sortedList){
                txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.NIM}\n\n")
                txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.nama}\n\n")
                txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.IPK}\n\n")
            }
        }





    }




}