package com.admin.turbocaretestassapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.admin.turbocaretestassapp.R
import com.admin.turbocaretestassapp.activity.HomeActivity
import com.admin.turbocaretestassapp.utils.Const
import com.google.android.material.floatingactionbutton.FloatingActionButton

class VehicleNumberFragment : Fragment(), View.OnClickListener {
    var toolbar: Toolbar? = null
    var navc: NavController? = null
    var edtVehicleNo: EditText? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        toolbar = activity?.findViewById(R.id.toolBar)
        (activity as HomeActivity).setSupportActionBar(toolbar)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_vehicle_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navc = Navigation.findNavController(view)
        view.findViewById<FloatingActionButton>(R.id.btn_VehicleNo)?.setOnClickListener(this)
        edtVehicleNo = view.findViewById<EditText>(R.id.edt_vehicleNo)
    }


    override fun onClick(v: View?) {

        if (edtVehicleNo?.text?.isNotEmpty() == true) {
            val bundle = Bundle()
            bundle.putString(Const.VNOKEY, edtVehicleNo?.text.toString())
            navc?.navigate(
                R.id.action_vehicleNumberFragment_to_vehicleClassSelectionFragment,
                bundle
            )

        } else {
            edtVehicleNo?.error = "Enter Vehicle No."
        }

    }

}