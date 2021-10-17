package id.ac.ukdw.playlist_wekk7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val listSong = arrayListOf<Song>()
    listSong.add(Song("Immanuel Sindu","0873217123747",  R.mipmap.bp1))
    listSong.add(Song("Tama Kristian","087747663882",  R.mipmap.bp2))
    listSong.add(Song("Suyanto","086663552773",  R.mipmap.bp3))
    listSong.add(Song("Mariatun","085552663772",  R.mipmap.bp1))
    listSong.add(Song("Junaedi","086663774889",  R.mipmap.bp2))
    listSong.add(Song("Marsinah", "087774676372" ,  R.mipmap.bp3))
    listSong.add(Song("Vito Cilik","0764452338928",  R.mipmap.bp1))
    listSong.add(Song("Vito Sedengan","0766635547872",  R.mipmap.bp2))
    listSong.add(Song("Narwoto","085554663721",   R.mipmap.bp3))
    listSong.add(Song("Munadi","088776373232",  R.mipmap.bp1))
    listSong.add(Song("Paimin Bakul Gabah","089997778667", R.mipmap.bp2))
    listSong.add(Song("Lek Jon","087774885776",  R.mipmap.bp3))

    val recySong = findViewById<RecyclerView>(R.id.RecyclerSong)
    recySong.layoutManager = LinearLayoutManager(this)
    val adapter = SongAdapter(listSong, this)
    recySong.adapter = adapter

    }
}