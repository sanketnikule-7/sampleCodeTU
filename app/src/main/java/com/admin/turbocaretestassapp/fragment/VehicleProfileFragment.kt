package com.admin.turbocaretestassapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.admin.turbocaretestassapp.R
import com.admin.turbocaretestassapp.adapter.VAdapter
import com.admin.turbocaretestassapp.model.VehicleItemModel
import com.admin.turbocaretestassapp.utils.Const

class VehicleProfileFragment : Fragment() {
    var toolbar: Toolbar? = null
    var navc: NavController? = null
    var vItemarrayList: VehicleItemModel? = null
    var textvehicleName: TextView? = null
    var textvehicleNo: TextView? = null
    var textmakename: TextView? = null
    var textmodelname: TextView? = null
    var textfullTypename: TextView? = null
    var texttransmissionname: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            vItemarrayList = bundle.getSerializable(Const.VDETAILKEY) as VehicleItemModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vehicle_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navc = Navigation.findNavController(view)
        textvehicleName = view.findViewById(R.id.txt_vehcleName) as TextView
        textvehicleNo = view.findViewById(R.id.txt_vehicleNo) as TextView
        textmakename = view.findViewById(R.id.text_makename) as TextView
        textmodelname = view.findViewById(R.id.text_modename) as TextView
        textfullTypename = view.findViewById(R.id.text_fuelType) as TextView
        texttransmissionname = view.findViewById(R.id.text_transmission) as TextView

        vItemarrayList?.let { showVehicleDetail(it) }


    }

    private fun showVehicleDetail(vItemarrayList: VehicleItemModel) {
        try {
            val vmodelName = vItemarrayList.vehicleModel
            val vfuelType = vItemarrayList.vehicleFuelType
            textvehicleName?.setText("$vmodelName $vfuelType")
            textvehicleNo?.setText(vItemarrayList.vehicleNo)
            textmakename?.setText(vItemarrayList.vehicleMake)
            textmodelname?.setText(vmodelName)
            textfullTypename?.setText(vfuelType)
            texttransmissionname?.setText(vItemarrayList.vehicleTransmission)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}