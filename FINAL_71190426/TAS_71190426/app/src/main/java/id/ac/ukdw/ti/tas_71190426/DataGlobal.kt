package id.ac.ukdw.ti.tas_71190426

import android.app.Application

class DataGlobal : Application() {

    companion object{
        var email_global = ""
        fun isiEmail(email: String){
            email_global = email
        }

        fun getEmail(): String{
            return email_global
        }

        var judulFilmLama = ""
        fun isiJudulFilmLama (judul :String){
            judulFilmLama = judul
        }

        fun getJudul(): String{
            return judulFilmLama
        }


    }
}