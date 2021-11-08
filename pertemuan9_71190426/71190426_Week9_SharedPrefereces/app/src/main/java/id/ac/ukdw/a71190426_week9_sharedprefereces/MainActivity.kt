package id.ac.ukdw.a71190426_week9_sharedprefereces

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var sp: SharedPreferences? = null
    var spEditor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = getSharedPreferences("week9sp", Context.MODE_PRIVATE)
        spEditor = sp?.edit()

        if (sp?.getBoolean("isLogin", false) == true) {
            setContentView(R.layout.activity_home)
            val spinnerBahasa = findViewById<Spinner>(R.id.spinner_bahasa)
            val adapterArray = ArrayAdapter.createFromResource(this,R.array.daftar_bahasa,R.layout.support_simple_spinner_dropdown_item)
            spinnerBahasa.adapter = adapterArray
            spinnerBahasa.setSelection(sp!!.getInt("bahasa",0))

            spinnerBahasa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                    spEditor?.putInt("bahasa", position)
                    spEditor?.commit()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            val ukuranFont = findViewById<TextView>(R.id.Edt_ukuranFont2)
            ukuranFont.text = sp?.getString("ukuran", "10")
            ukuranFont.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    spEditor?.putString("ukuran", s.toString())
                    spEditor?.commit()

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })

        } else {
            setContentView(R.layout.activity_main)
            val edtUsername = findViewById<EditText>(R.id.edt_username)
            val edtPassword = findViewById<EditText>(R.id.edt_password)
            val buttonLogin = findViewById<Button>(R.id.btn_login)

            buttonLogin.setOnClickListener {
                if (edtUsername.text.toString() == "admin" && edtPassword.text.toString() == "admin") {
                    spEditor?.putBoolean("isLogin", true)
                    spEditor?.commit()

                    val relog = Intent(this, MainActivity::class.java)
                    startActivity(relog)
                    finish()
                }
            }
        }
    }
}