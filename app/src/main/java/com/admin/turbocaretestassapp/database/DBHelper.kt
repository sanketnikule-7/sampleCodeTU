package com.admin.turbocaretestassapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val SQL_CREATE_VEHICLE_DATA_TABLE = (
            "CREATE TABLE " + DataProvider.VehicleList.TABLE_NAME + "("
                    + DataProvider.VehicleList.id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DataProvider.VehicleList.vehicleName + " TEXT,"
                    + DataProvider.VehicleList.vehicleNo + " TEXT,"
                    + DataProvider.VehicleList.vehicleClass + " TEXT,"
                    + DataProvider.VehicleList.vehicleMake + " TEXT,"
                    + DataProvider.VehicleList.vehicleModel + " TEXT,"
                    + DataProvider.VehicleList.vehicleFuelType + " TEXT,"
                    + DataProvider.VehicleList.vehicleTransmission + " TEXT"
                    + ")")

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_VEHICLE_DATA_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


    fun insertVehicleData(values: ContentValues?): Long {
        Log.d(TAG, "InsertVehicleData")
        val db = this.writableDatabase
        val success = db.insert(DataProvider.VehicleList.TABLE_NAME, null, values)
        db.close()

        return success
    }

    fun getVehicleList(): JSONArray {
        val db = readableDatabase
        val cursor =
            db!!.query(
                DataProvider.VehicleList.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        val resultSet: JSONArray = JSONArray()
        cursor.moveToFirst()
        while (cursor.isAfterLast() == false) {
            val totalColumn = cursor.columnCount
            val rowObject = JSONObject()
            for (i in 0 until totalColumn) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null) {
                            Log.d("resssDataBaseNewEmpList", cursor.getString(i))
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i))
                        } else {
                            rowObject.put(cursor.getColumnName(i), "")
                        }
                    } catch (e: Exception) {
                        Log.d("resssDataBaseNewEmpList", e.message.toString())
                    }
                }
            }
            resultSet.put(rowObject)
            cursor.moveToNext()
        }
        cursor.close()
        return resultSet
    }

    companion object {
        private val DATABASE_NAME = "turbocaretestassapp.db"
        private val DATABASE_VERSION = 1
        private val TAG = "DBHelper"
    }
}

