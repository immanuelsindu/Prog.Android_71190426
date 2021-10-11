package id.ac.ukdw.a71190426_week6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Afragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val infla = inflater.inflate(R.layout.fragment_a, container, false)
        val buttonPindahHalaman = infla.findViewById<Button>(R.id.button_pindahHal2)

        buttonPindahHalaman.setOnClickListener {
           val intent = Intent(this@Afragment.context, Halaman2Activity::class.java)
            getActivity()?.startActivity(intent)
        }

        return infla
    }
}