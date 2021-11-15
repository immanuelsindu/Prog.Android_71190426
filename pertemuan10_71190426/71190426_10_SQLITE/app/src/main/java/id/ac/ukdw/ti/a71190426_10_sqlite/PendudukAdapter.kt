package id.ac.ukdw.ti.a71190426_10_sqlite

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class PendudukAdapter(var listPenduduk: ArrayList<String>, var db : SQLiteDatabase): RecyclerView.Adapter<PendudukAdapter.PendudukHolder>() {
    class PendudukHolder(val v: View, val db: SQLiteDatabase): RecyclerView.ViewHolder(v){


        fun bindView(data : String){
            val txtInfo = v.findViewById<TextView>(R.id.txtInfo)
            txtInfo.setText(data)

            val btnHapus = v.findViewById<Button>(R.id.btnHapus)
            btnHapus.setOnClickListener{
                val selection = "${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? "
                val dataArray = data.split(" (")
                val selectionArgs = arrayOf(dataArray[0])
                db.delete(DatabaseContract.Penduduk.TABLE_NAME, selection,selectionArgs)




            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendudukHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_penduduk, parent, false)
        return PendudukHolder(view, db)
    }

    override fun onBindViewHolder(holder: PendudukHolder, position: Int) {
        holder.bindView(listPenduduk.get(position))
    }

    override fun getItemCount(): Int = listPenduduk.size
}