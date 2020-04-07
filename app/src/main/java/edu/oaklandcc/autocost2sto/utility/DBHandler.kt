package edu.oaklandcc.autocost2sto.utility

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import edu.oaklandcc.autocost2sto.model.Fillup

class DBHandler(
    context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        private val DATABASE_VERSION: Int = 1
        private val DATABAE_NAME: String = "fillupsDB.db"
        val TABLE_FILLUP: String = "fillup"
        val COLUMN_ID: String = "id"
        val COLUMN_COST: String = "cost"
        val COLUMN_GALLONS: String = "gallons"
        val COLUMN_ODOMETER: String = "odometer"
        val COLUMN_DATE: String = "odometer"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE ${TABLE_FILLUP} (${COLUMN_ID} INTEGER PRIMARY KEY, " +
                "${COLUMN_COST} REAL, ${COLUMN_GALLONS} REAL, ${COLUMN_ODOMETER} REAL, ${COLUMN_DATE} TEXT);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val query = "DROP TABLE IF EXISTS ${TABLE_FILLUP};"
        db?.execSQL(query)
    }

    fun addFillup(fillup: Fillup) {
        var values = ContentValues()
        values.put(COLUMN_COST, fillup.cost)
        values.put(COLUMN_DATE, fillup.date)
        values.put(COLUMN_GALLONS, fillup.gas)
        values.put(COLUMN_ODOMETER, fillup.odometer)
        val db = writableDatabase
        db.insert(TABLE_FILLUP, null, values)
        db.close()
    }

    fun deleteFillup(fillup: Fillup) {

    }


}