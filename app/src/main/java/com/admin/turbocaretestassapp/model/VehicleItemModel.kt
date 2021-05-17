package com.admin.turbocaretestassapp.model

import java.io.Serializable

data class VehicleItemModel(
    val vehicleName: String,
    val vehicleNo: String,
    val vehicleClass: String,
    val vehicleMake: String,
    val vehicleModel: String,
    val vehicleFuelType: String,
    val vehicleTransmission: String,
) : Serializable {
}