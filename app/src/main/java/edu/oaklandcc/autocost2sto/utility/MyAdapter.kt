package edu.oaklandcc.autocost2sto.utility

import android.content.Intent
import android.view.*
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import edu.oaklandcc.autocost2sto.R
import edu.oaklandcc.autocost2sto.model.Fillup
import edu.oaklandcc.autocost2sto.model.Model
import edu.oaklandcc.autocost2sto.viewControl.GasActivity
import edu.oaklandcc.autocost2sto.viewControl.MainActivity

class MyAdapter(
    var fillupList: ArrayList<Fillup>,
    val mainActivity: MainActivity
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val myView: CardView, val activity: MainActivity) :
        RecyclerView.ViewHolder(myView),
        MenuItem.OnMenuItemClickListener, View.OnCreateContextMenuListener {
        var textCost: TextView = myView.findViewById(R.id.textView_card_cost)
        var textGallons: TextView = myView.findViewById(R.id.textView_card_gallons)
        var textDate: TextView = myView.findViewById(R.id.textView_card_date)
        var textMileage: TextView = myView.findViewById(R.id.textView_card_odometer)

        override fun onMenuItemClick(p0: MenuItem?): Boolean {
            val pos = adapterPosition
            Model.removeEntry(pos)
            activity.updateAdapterData()
            return true
        }

        override fun onCreateContextMenu(
            p0: ContextMenu?, p1: View?,
            p2: ContextMenu.ContextMenuInfo?
        ) {
            val menuItem = p0?.add("Delete")
            menuItem?.setOnMenuItemClickListener(this)
        }

        init {
            itemView.setOnCreateContextMenuListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val all = LayoutInflater.from(parent.context)
            .inflate(R.layout.entry_card, parent, false) as CardView
        return MyViewHolder(all, mainActivity)
    }

    override fun getItemCount(): Int = fillupList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textMileage.text = String.format("%,.1f miles", fillupList[position].odometer)
        holder.textCost.text = String.format("$%,.2f", fillupList[position].cost)
        holder.textGallons.text = String.format("%,.3f gallons", fillupList[position].gas)
        holder.textDate.text = fillupList[position].date

        val context = holder.textCost.context

        holder.myView.setOnClickListener {
            val statsIntent = Intent(context, GasActivity::class.java)
            statsIntent.putExtra("listPosition", position)
            context.startActivity(statsIntent)
        }
    }
}