package id.ac.ukdw.a71190426_week6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Bfragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val infla = inflater.inflate(R.layout.fragment_b, container, false)
        val buttonPindahHalaman = infla.findViewById<Button>(R.id.button_pindahHal3)

        buttonPindahHalaman.setOnClickListener {
            val intent = Intent(this@Bfragment.context, Halaman3Activity::class.java)
            getActivity()?.startActivity(intent)
        }

        return infla
    }
}