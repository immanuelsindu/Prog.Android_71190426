package id.ac.ukdw.ti.a71190426_10_sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var dbHelper : SQLiteOpenHelper? = null
    var db:SQLiteDatabase? = null
    var listPenduduk = ArrayList<String>()
    var adapter: PendudukAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        db = dbHelper?.writableDatabase

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtUsia = findViewById<EditText>(R.id.edtUsia)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnCari = findViewById<Button>(R.id.btnCari)

        btnCari.setOnClickListener{
            val listDataHapus = ArrayList<String>()
            listDataHapus.add(edtNama.text.toString())
            listDataHapus.add(edtUsia.text.toString())
            reloadData(this, 2, listDataHapus)
        }

        btnSimpan.setOnClickListener{
            if(edtNama.text.toString() != "" && edtUsia.text.toString() != ""){
                val values = ContentValues()
                values.put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA, edtNama.text.toString())
                values.put(DatabaseContract.Penduduk.COLUMN_NAME_USIA, edtUsia.text.toString())

                db?.insert(DatabaseContract.Penduduk.TABLE_NAME, null, values)
                edtNama.setText("")
                edtUsia.setText("")
                reloadData(this, 1, null)
            }else{
                Toast.makeText(this, "Nama dan Usia tidak boleh kosong saat menyimpan data", Toast.LENGTH_LONG).show()
            }

        }



        val rcyPenduduk = findViewById<RecyclerView>(R.id.rcyPenduduk)
        rcyPenduduk.layoutManager = LinearLayoutManager(this)
        adapter = PendudukAdapter(listPenduduk, db!!)
        rcyPenduduk.adapter = adapter

        reloadData(this, 1, null)
    }

    @SuppressLint("Range")
    fun reloadData(context: Context, pilihan: Int, IDHapus: ArrayList<String>?){
       listPenduduk.clear()

        val columns = arrayOf(
            BaseColumns._ID,
            DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA,
        )

        var cursor : Cursor? = null
        if(pilihan == 1 ){
            cursor = db?.query(
                DatabaseContract.Penduduk.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
            )

        }else if(pilihan == 2){
            val selection = "${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? OR ${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ? "
            cursor = db?.query(
                DatabaseContract.Penduduk.TABLE_NAME,
                columns,
                selection,
                arrayOf(IDHapus?.get(0), IDHapus?.get(1)),
                null,
                null,
                null)

        }

        with(cursor!!){
            while(moveToNext()){
                val nama = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_NAMA))
                val usia = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_USIA))
                listPenduduk.add("${nama} (${usia})")
                Log.d("SQLITE", "${nama} (${usia})")
            }
            adapter?.notifyDataSetChanged()
        }
    }
}