package com.admin.turbocaretestassapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.admin.turbocaretestassapp.adapter.VehicleListAdapter
import com.admin.turbocaretestassapp.database.DBHelper
import com.admin.turbocaretestassapp.model.VehicleItemModel
import com.admin.turbocaretestassapp.utils.Const
import com.admin.turbocaretestassapp.utils.Listener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class VehicleListFragment : Fragment(), View.OnClickListener, Listener {

    var navc: NavController? = null
    var toolbar: Toolbar? = null
    var stringlist = ArrayList<VehicleItemModel>()
    var adapter: VehicleListAdapter? = null
    lateinit var vehicleRecyclerView: RecyclerView
    var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = DBHelper(requireContext().applicationContext)

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
        return inflater.inflate(R.layout.fragment_vehicle_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("cccccc","ffff")
        navc = Navigation.findNavController(view)
        view.findViewById<FloatingActionButton>(R.id.btn_addNewVehicle)
            ?.setOnClickListener(this)
        vehicleRecyclerView =
            view.findViewById(R.id.rView_vehicle) as RecyclerView
        vehicleRecyclerView.layoutManager =
            LinearLayoutManager(requireContext().applicationContext, LinearLayoutManager.VERTICAL, false)
        stringlist = ArrayList()
    }

    override fun onResume() {
        super.onResume()
        loadVVehicleList(requireContext().applicationContext)
    }

    fun loadVVehicleList(context: Context) {
        stringlist.clear()

        val vehicleListData = dbHelper?.getVehicleList()
        for (i in 0 until vehicleListData!!.length()) {
            stringlist.add(
                VehicleItemModel(
                    vehicleListData.getJSONObject(i).getString("vehicleName"),
                    vehicleListData.getJSONObject(i).getString("vehicleNo"),
                    vehicleListData.getJSONObject(i).getString("vehicleClass"),
                    vehicleListData.getJSONObject(i).getString("vehicleMake"),
                    vehicleListData.getJSONObject(i).getString("vehicleModel"),
                    vehicleListData.getJSONObject(i).getString("vehicleFuelType"),
                    vehicleListData.getJSONObject(i).getString("vehicleTransmission")
                )
            )
        }

            Log.d("cccccc","ffff null")
            adapter =
                VehicleListAdapter(
                    context,
                    stringlist,
                    this@VehicleListFragment,
                )
            vehicleRecyclerView.adapter = adapter
    }

    override fun onClick(v: View?) {
        navc?.navigate(R.id.action_vehicleListFragment_to_vehicleNumberFragment)

    }

    override fun onClickItemListener(position: Int) {
        val vehicleItem = stringlist.get(position)
        val bundle = Bundle()
        bundle.putSerializable(Const.VDETAILKEY, vehicleItem)
        navc?.navigate(R.id.action_vehicleListFragment_to_vehicleProfileFragment, bundle)
    }

}