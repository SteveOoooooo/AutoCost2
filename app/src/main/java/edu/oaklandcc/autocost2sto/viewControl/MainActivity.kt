package edu.oaklandcc.autocost2sto.viewControl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.oaklandcc.autocost2sto.R
import edu.oaklandcc.autocost2sto.model.Model
import edu.oaklandcc.autocost2sto.utility.MyAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var layoutMgr : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val gasIntent = Intent(this, GasActivity::class.java)
            startActivity(gasIntent)
        }

        layoutMgr = LinearLayoutManager(this)
        recycler1.apply {
            layoutManager = layoutMgr
            adapter = MyAdapter(Model.list)
        }
    }

    override fun onResume() {
        super.onResume()
        recycler1.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_showStats -> {
                showStats()
                true
            }
            R.id.action_deleteAll ->{
                deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showStats() {
        val statsIntent = Intent(this, StatisticsActivity::class.java)
        startActivity(statsIntent)
    }

    private fun deleteAll() {
        Model.list.clear()
        recycler1.adapter?.notifyDataSetChanged()
    }
}
