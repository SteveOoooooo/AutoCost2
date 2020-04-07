package edu.oaklandcc.autocost2sto.model

object Model {
    var list = ArrayList<Fillup>()

    fun addToList(fillup: Fillup) {
        list.add(fillup)
    }

    fun removeEntry(index: Int) {
        list.removeAt(index)
    }
}