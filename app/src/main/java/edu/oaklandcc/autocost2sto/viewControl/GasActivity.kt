package edu.oaklandcc.autocost2sto.viewControl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import edu.oaklandcc.autocost2sto.R
import edu.oaklandcc.autocost2sto.model.Fillup
import edu.oaklandcc.autocost2sto.model.Model
import kotlinx.android.synthetic.main.activity_gas.*
import java.text.SimpleDateFormat
import java.util.*

class GasActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gas)

        val listPostition = intent.getIntExtra("listPosition", -1)

        if (listPostition > -1){
            editText_gas_cost.setText(Model.list[listPostition].cost.toString())
            editText_gas_gas.setText(Model.list[listPostition].gas.toString())
            editText_gas_odometer.setText(Model.list[listPostition].odometer.toString())

            button_addSaveEntry.setText(R.string.save)
            textView_gas_title.setText(R.string.edit)
            button_addSaveEntry.isEnabled = true
        }
        else
        {
            button_addSaveEntry.setText(R.string.add)
            textView_gas_title.setText(R.string.add)
            button_addSaveEntry.isEnabled = false
        }

        button_addSaveEntry.setOnClickListener{
            if (listPostition > -1){
                Model.list[listPostition].gas = editText_gas_gas.text.toString().toDouble()
                Model.list[listPostition].odometer = editText_gas_odometer.text.toString().toDouble()
                Model.list[listPostition].cost = editText_gas_cost.text.toString().toDouble()
            }
            else {

                val dateFormat = SimpleDateFormat("MMM d, yyyy")
                val stringDate = dateFormat.format(Date())

                Model.addToList(Fillup(editText_gas_odometer.text.toString().toDouble(),
                    editText_gas_gas.text.toString().toDouble(), editText_gas_cost.text.toString().toDouble(), stringDate
                    )
                )
            }
            this.finish()
        }

        editText_gas_cost.addTextChangedListener{
            validTextCheck()
        }

        editText_gas_gas.addTextChangedListener{
            validTextCheck()
        }

        editText_gas_odometer.addTextChangedListener{
            validTextCheck()
        }
    }

    private fun validTextCheck() {
        button_addSaveEntry.isEnabled = (editText_gas_cost.text.toString().toDoubleOrNull() != null) &&
                (editText_gas_gas.text.toString().toDoubleOrNull() != null) &&
                (editText_gas_odometer.text.toString().toDoubleOrNull() != null)
    }
}