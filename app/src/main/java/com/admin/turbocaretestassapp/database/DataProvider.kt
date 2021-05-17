package com.admin.turbocaretestassapp.database

import android.provider.BaseColumns

object DataProvider {

    class VehicleList : BaseColumns {
        companion object {
            val TABLE_NAME = "tbl_vehicle"
            val id = BaseColumns._ID
            val vehicleName = "vehicleName"
            val vehicleNo = "vehicleNo"
            val vehicleClass = "vehicleClass"
            val vehicleMake = "vehicleMake"
            val vehicleModel = "vehicleModel"
            val vehicleFuelType = "vehicleFuelType"
            val vehicleTransmission = "vehicleTransmission"
        }
    }
}