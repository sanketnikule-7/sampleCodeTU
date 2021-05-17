package org.qisystems.honda.WebServices

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("makes?")
    fun getVehicleMakeListData(
        @Query("class") className: String,
    ): Call<ArrayList<String>>

    @GET("models?")
    fun getVehicleModelListData(
        @Query("class") className: String, @Query("make") makeName: String,
    ): Call<ArrayList<String>>
}