package com.admin.turbocaretestassapp.fragment

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.admin.turbocaretestassapp.R
import com.admin.turbocaretestassapp.activity.HomeActivity
import com.admin.turbocaretestassapp.adapter.VAdapter
import com.admin.turbocaretestassapp.database.DBHelper
import com.admin.turbocaretestassapp.database.DataProvider
import com.admin.turbocaretestassapp.utils.Const
import com.admin.turbocaretestassapp.utils.Listener

class VehicleTransmissionSelectionFragment : Fragment(), Listener {
    var toolbar: Toolbar? = null
    var navc: NavController? = null
    var stringlist = ArrayList<String>()
    var adapter: VAdapter? = null
    lateinit var modelRecyclerView: RecyclerView
    var vNoValue = ""
    var vClassValue = ""
    var vMakeValue = ""
    var vModelValue = ""
    var vFuelTypeValue = ""
    var dbHelper: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = DBHelper(requireContext().applicationContext)
        val bundle = this.arguments
        if (bundle != null) {
            vNoValue = bundle.getString(Const.VNOKEY, "")
            vClassValue = bundle.getString(Const.VCLASSKEY, "")
            vMakeValue = bundle.getString(Const.VMAKEKEY, "")
            vModelValue = bundle.getString(Const.VMODELKEY, "")
            vFuelTypeValue = bundle.getString(Const.VFUELTYPEKEY, "")

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        toolbar = activity?.findViewById(R.id.toolBar)
        (activity as HomeActivity).setSupportActionBar(toolbar)
        super.onActivityCreated(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vehicle_transmission_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navc = Navigation.findNavController(view)
        modelRecyclerView =
            view.findViewById(R.id.rv_transmission) as RecyclerView
        modelRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        stringlist = ArrayList()
    }

    override fun onResume() {
        super.onResume()
        loadVModelList(context)
    }

    fun loadVModelList(context: Context?) {
        stringlist.clear()
        stringlist = arrayListOf("Manual", "Automatic")
        adapter =
            VAdapter(
                context,
                stringlist,
                this@VehicleTransmissionSelectionFragment,
            )
        modelRecyclerView.adapter = adapter
    }

    override fun onClickItemListener(position: Int) {
        val values = ContentValues()
        values.put(DataProvider.VehicleList.vehicleNo, vNoValue)
        values.put(DataProvider.VehicleList.vehicleClass, vClassValue)
        values.put(DataProvider.VehicleList.vehicleMake, vMakeValue)
        values.put(DataProvider.VehicleList.vehicleModel, vModelValue)
        values.put(DataProvider.VehicleList.vehicleFuelType, vFuelTypeValue)
        values.put(DataProvider.VehicleList.vehicleTransmission, stringlist.get(position))
        val result = dbHelper?.insertVehicleData(values)
        if (result != null) {
            if (result > -1) {
                Toast.makeText(activity, "Vehicle Added Succesfully!!!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Some Issue!!!", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(activity, "Some Problem!!!", Toast.LENGTH_LONG).show()
        }
        navc?.popBackStack(R.id.vehicleListFragment, false)
    }

}