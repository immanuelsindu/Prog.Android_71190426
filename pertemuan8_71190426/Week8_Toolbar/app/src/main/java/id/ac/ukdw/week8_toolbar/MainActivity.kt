package id.ac.ukdw.week8_toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStateManagerControl
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar_default))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val listFragment = arrayListOf<Fragment>(FragmentSatu() , FragmentDua())
        val pager = findViewById<ViewPager2>(R.id.pager)
        val pagerAdapter = PagerAdapter(this,listFragment)
        pager.adapter = pagerAdapter

    }

    class PagerAdapter(val activity: AppCompatActivity, val listFragment : ArrayList<Fragment>) :FragmentStateAdapter(activity){
        override fun getItemCount() = listFragment.size

        override fun createFragment(position: Int): Fragment = listFragment.get(position)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_default, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_satu -> {
            Toast.makeText(this, "Ini Menu Satu", Toast.LENGTH_LONG).show()
            true
        }
        R.id.menu_dua -> {
            Toast.makeText(this, "Ini Menu Dua", Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}