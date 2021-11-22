package id.ac.ukdw.ti.a71190426_firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MahasiswaAdapter(val listMahasiswa: ArrayList<Mahasiswa>):RecyclerView.Adapter<MahasiswaAdapter.MahasiswaHolder>() {
    class MahasiswaHolder(val view: View):RecyclerView.ViewHolder(view) {
        var mahasiswa: Mahasiswa? = null

        fun bindView(mahasiswa :Mahasiswa){
            this.mahasiswa = mahasiswa
            view.findViewById<TextView>(R.id.edtNim2).setText("${mahasiswa.NIM}")
            view.findViewById<TextView>(R.id.edtNama2).setText("${mahasiswa.nama}")
            view.findViewById<TextView>(R.id.edtIpk2).setText("${mahasiswa.IPK}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.isian_data, parent, false)
        return MahasiswaHolder(v)
    }

    override fun onBindViewHolder(holder: MahasiswaHolder, position: Int) {
        holder.bindView(listMahasiswa[position])
    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }
}