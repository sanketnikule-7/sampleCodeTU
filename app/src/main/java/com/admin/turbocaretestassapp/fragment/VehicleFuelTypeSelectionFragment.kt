package com.admin.turbocaretestassapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.admin.turbocaretestassapp.R
import com.admin.turbocaretestassapp.activity.HomeActivity
import com.admin.turbocaretestassapp.adapter.VAdapter
import com.admin.turbocaretestassapp.utils.Const
import com.admin.turbocaretestassapp.utils.Listener

class VehicleFuelTypeSelectionFragment : Fragment(), Listener {
    var toolbar: Toolbar? = null
    var navc: NavController? = null
    var stringlist = ArrayList<String>()
    var adapter: VAdapter? = null
    lateinit var modelRecyclerView: RecyclerView
    var vNoValue = ""
    var vClassValue = ""
    var vMakeValue = ""
    var vModelValue = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            vNoValue = bundle.getString(Const.VNOKEY, "")
            vClassValue = bundle.getString(Const.VCLASSKEY, "")
            vMakeValue = bundle.getString(Const.VMAKEKEY, "")
            vModelValue = bundle.getString(Const.VMODELKEY, "")
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
        return inflater.inflate(R.layout.fragment_vehicle_fuel_type_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navc = Navigation.findNavController(view)
        modelRecyclerView =
            view.findViewById(R.id.rv_fuelType) as RecyclerView
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
        stringlist = arrayListOf("Petrol", "Diesel", "CNG", "Petrol + CNG", "Electric", "Hybrid")
        adapter =
            VAdapter(
                context,
                stringlist,
                this@VehicleFuelTypeSelectionFragment,
            )
        modelRecyclerView.adapter = adapter
    }

    override fun onClickItemListener(position: Int) {
        val bundle = Bundle()
        bundle.putString(Const.VNOKEY,vNoValue)
        bundle.putString(Const.VCLASSKEY, vClassValue)
        bundle.putString(Const.VMAKEKEY, vMakeValue)
        bundle.putString(Const.VMODELKEY, vModelValue)
        bundle.putString(Const.VFUELTYPEKEY, stringlist.get(position))
        navc?.navigate(
            R.id.action_vehicleFuelTypeSelectionFragment_to_vehicleTransmissionSelectionFragment,
            bundle
        )
    }

}