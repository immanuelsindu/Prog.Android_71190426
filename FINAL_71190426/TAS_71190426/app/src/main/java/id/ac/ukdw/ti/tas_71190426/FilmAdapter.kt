package id.ac.ukdw.ti.tas_71190426

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.common.io.LineReader
import com.google.firebase.firestore.FirebaseFirestore
var firestore: FirebaseFirestore? = null
class FilmAdapter(var listFilm: ArrayList<POJO_Film>, val context: Context): RecyclerView.Adapter<FilmAdapter.FilmHolder>(){

    class FilmHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(film: POJO_Film, context: Context){
            view.findViewById<TextView>(R.id.itemJudulFilm).setText("${film.judulFilm}")
            view.findViewById<TextView>(R.id.itemRating).setText("${film.rating}")
            view.findViewById<TextView>(R.id.itemTanggalRilis).setText("${film.tanggalRilis}")
            view.findViewById<TextView>(R.id.itemKategori).setText("${film.kategori}")

            val menujuUpdate = view.findViewById<LinearLayout>(R.id.Layout_ItemFilm)

            menujuUpdate.setOnClickListener{
                val intent = Intent(context,UpdateFilm::class.java)
                intent.putExtra("judulFilm",film.judulFilm)
                intent.putExtra("rating",film.rating)
                intent.putExtra("tanggalRilis",film.tanggalRilis)
                intent.putExtra("kategori",film.kategori)
                DataGlobal.isiJudulFilmLama(film.judulFilm)

                context.startActivities(arrayOf(intent))
            }


            val btnHapus = view.findViewById<Button>(R.id.itemBtnHapus)
            firestore = FirebaseFirestore.getInstance()

            btnHapus.setOnClickListener {
                firestore?.collection("Film")?.whereEqualTo("judulFilm", film.judulFilm)?.get()?.
                addOnSuccessListener {hasil->
                    for(i in hasil){
                        firestore?.collection("Film")?.document(i.id)?.delete()
                            ?.addOnSuccessListener {
                                Toast.makeText(context,"Film ${film.judulFilm} berhasil dihapus",Toast.LENGTH_LONG).show()
                                val intent = Intent(context,home::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                context.startActivities(arrayOf(intent))
                            }
                    }
                    }
            }





    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_film,parent, false)
        return FilmHolder(v)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(listFilm[position],context)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }
}