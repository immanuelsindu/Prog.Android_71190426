package id.ac.ukdw.playlist_wekk7

import android.app.PendingIntent.getActivity
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import android.content.Intent
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity

class SongAdapter(var listSong: ArrayList<Song>, val context: Context): RecyclerView.Adapter<SongAdapter.SongHolder>(){
    class SongHolder(val view: View) :RecyclerView.ViewHolder(view){
        fun bind(song: Song, context: Context) {
            view.findViewById<ImageView>(R.id.img_cover).setImageResource(song.cover)
            view.findViewById<TextView>(R.id.text_Judul).setText("${song.title}")
            view.findViewById<TextView>(R.id.text_Penyannyi).setText("${song.singer}")

            val test = view.findViewById<LinearLayout>(R.id.wadah_Linear)

            test.setOnClickListener {
                val myIntent = Intent(context, DetailKontakActivity::class.java)
                myIntent.putExtra("Nomer", song.singer)
                myIntent.putExtra("Nama", song.title)
                myIntent.putExtra("Cover", song.cover)
                context.startActivity(myIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongHolder(v)
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(listSong[position], context)

    }

    override fun getItemCount(): Int {
        return listSong.size
    }
}