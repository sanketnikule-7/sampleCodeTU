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

class VehicleClassSelectionFragment : Fragment(), Listener {
    var toolbar: Toolbar? = null
    var navc: NavController? = null
    var stringlist = ArrayList<String>()
    var adapter: VAdapter? = null
    lateinit var classRecyclerView: RecyclerView
    var vNoValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            vNoValue = bundle.getString(Const.VNOKEY, "")
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
        return inflater.inflate(R.layout.fragment_vehicle_class_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navc = Navigation.findNavController(view)

        classRecyclerView =
            view.findViewById(R.id.rv_class) as RecyclerView
        classRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        stringlist = ArrayList()
    }

    override fun onResume() {
        super.onResume()
        loadClassList(context)
    }

    fun loadClassList(context: Context?) {
        stringlist.clear()
        stringlist = arrayListOf("2w", "4w")
        adapter =
            VAdapter(
                context,
                stringlist,
                this,
            )
        classRecyclerView.adapter = adapter
    }


    override fun onClickItemListener(position: Int) {
        val bundle = Bundle()
        bundle.putString(Const.VNOKEY, vNoValue)
        bundle.putString(Const.VCLASSKEY, stringlist.get(position))
        navc?.navigate(
            R.id.action_vehicleClassSelectionFragment_to_vehicleMakeSelectionFragment,
            bundle
        )
    }

}