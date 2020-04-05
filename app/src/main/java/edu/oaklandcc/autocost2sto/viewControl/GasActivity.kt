package edu.oaklandcc.autocost2sto.viewControl

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.oaklandcc.autocost2sto.R
import edu.oaklandcc.autocost2sto.model.Fillup
import edu.oaklandcc.autocost2sto.model.Model
import edu.oaklandcc.autocost2sto.utility.MyAdapter
import kotlinx.android.synthetic.main.activity_gas.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class GasActivity() : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gas)

        val addButton = findViewById<Button>(R.id.button_addEntry)
        val costEditText = findViewById<EditText>(R.id.editText_Cost)

        addButton.setOnClickListener{
            Model.addToList(
                Fillup(
                    editText_odometer.text.toString().toDouble(),
                    editText_Gas.text.toString().toDouble(),
                    costEditText.text.toString().toDouble(),
                    Date()
                )
            )
            this.finish()
        }
    }
}