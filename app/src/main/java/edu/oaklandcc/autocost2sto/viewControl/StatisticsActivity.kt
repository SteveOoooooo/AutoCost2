package edu.oaklandcc.autocost2sto.viewControl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import edu.oaklandcc.autocost2sto.R
import edu.oaklandcc.autocost2sto.model.Model

class StatisticsActivity : AppCompatActivity() {

    lateinit var totalFillups : TextView
    lateinit var totalGas : TextView
    lateinit var totalOdo : TextView
    lateinit var totalCost : TextView
    lateinit var lastMPG : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        totalFillups = findViewById(R.id.textView_stats_filupTotal)
        totalGas = findViewById(R.id.textView_stats_gasTotal)
        totalOdo = findViewById(R.id.textView_stats_odometerTotal)
        totalCost = findViewById(R.id.textView_stats_costTotal)
        lastMPG = findViewById(R.id.textView_stats_lastMPG)

        totalFillups.text = getTotalFillups()
        totalGas.text = getTotalGas()
        totalCost.text = getTotalCost()
        totalOdo.text = getTotalOdo()
        lastMPG.text = getLastMPG()

    }

    private fun getTotalFillups() : String = "Totals over ${Model.list.size} fill-ups"

    private fun getTotalGas() : String{
        return if (Model.list.size == 0) {
            "0 gallons"
        } else {
            String.format("%,.3f gallons", Model.list.sumByDouble { it.gas })
        }
    }

    private fun getTotalOdo() : String {
        return if (Model.list.size > 1) {
            String.format("%,.1f miles", Model.list.last().odometer - Model.list[0].odometer)
        } else{
            "0 miles"
        }
    }

    private fun getTotalCost() : String {
        return if (Model.list.size == 0) {
            "$0.00"
        } else {
            String.format("$%,.2f" , Model.list.sumByDouble { it.cost })
        }
    }

    private fun getLastMPG() : String {
        return if (Model.list.size < 2) {
            "N/A"
        } else {
            val last = Model.list.lastIndex
            val mpg = (Model.list[last].odometer - Model.list[last - 1].odometer)/ Model.list[last].gas
            return String.format("$%,.2f", mpg)
        }
    }
}
