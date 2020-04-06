package edu.oaklandcc.autocost2sto.utility

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import edu.oaklandcc.autocost2sto.R
import edu.oaklandcc.autocost2sto.model.Fillup
import edu.oaklandcc.autocost2sto.model.Model
import edu.oaklandcc.autocost2sto.viewControl.GasActivity
import edu.oaklandcc.autocost2sto.viewControl.MainActivity
import edu.oaklandcc.autocost2sto.viewControl.StatisticsActivity
import java.text.SimpleDateFormat

class MyAdapter(var fillupList: ArrayList<Fillup>, var mainActivityContext: Context): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    class MyViewHolder(val myView: CardView)
        : RecyclerView.ViewHolder(myView),
        MenuItem.OnMenuItemClickListener, View.OnCreateContextMenuListener {
        var textCost = myView.findViewById<TextView>(R.id.textView_cost)
        var textGallons = myView.findViewById<TextView>(R.id.textView_gallons)
        var textDate = myView.findViewById<TextView>(R.id.textView_date)
        var textMileage = myView.findViewById<TextView>(R.id.textView_odometer)

        override fun onMenuItemClick(p0: MenuItem?): Boolean {
            var pos = adapterPosition
            Model.removeEntry(pos)
            return true
        }

        override fun onCreateContextMenu(p0: ContextMenu?, p1: View?,
                                         p2: ContextMenu.ContextMenuInfo?) {
            var menuItem = p0?.add("Delete")
            menuItem?.setOnMenuItemClickListener(this)
        }

        init{
            itemView.setOnCreateContextMenuListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val all = LayoutInflater.from(parent.context).inflate(R.layout.entry_card, parent, false) as CardView
        return MyViewHolder(all)
    }

    override fun getItemCount(): Int = fillupList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textMileage.text = String.format("%,.1f miles", fillupList[position].odometer )
        holder.textCost.text = String.format("$%,.2f", fillupList[position].cost)
        holder.textGallons.text = String.format("%,.3f gallons", fillupList[position].gas)

        val dateFormat = SimpleDateFormat("MMM d, yyyy")
        holder.textDate.text = dateFormat.format(fillupList[position].date)

        val context = holder.textCost.context

        holder.myView.setOnClickListener {
            val statsIntent = Intent(context, GasActivity::class.java)
            statsIntent.putExtra("listPosition", position)
            context.startActivity(statsIntent)
        }
    }
}