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
import com.admin.turbocaretestassapp.utils.Const
import com.admin.turbocaretestassapp.utils.Listener
import org.qisystems.honda.WebServices.ApiInterface
import org.qisystems.honda.WebServices.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VehicleModelSelectionFragment : Fragment(), Listener {
    var toolbar: Toolbar? = null
    var navc: NavController? = null
    var apiInterface: ApiInterface? = null
    var stringlist = ArrayList<String>()
    var adapter: VAdapter? = null
    lateinit var modelRecyclerView: RecyclerView
    var vNoValue = ""
    var vClassValue = ""
    var vMakeValue = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            vNoValue = bundle.getString(Const.VNOKEY, "")
            vClassValue = bundle.getString(Const.VCLASSKEY, "")
            vMakeValue = bundle.getString(Const.VMAKEKEY, "")
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
        return inflater.inflate(R.layout.fragment_vehicle_model_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navc = Navigation.findNavController(view)
        apiInterface = RetrofitClient.getClient(requireContext().applicationContext)
            ?.create(ApiInterface::class.java)
        modelRecyclerView =
            view.findViewById(R.id.rv_model) as RecyclerView
        modelRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        stringlist = ArrayList()
    }

    override fun onResume() {
        super.onResume()
        loadVModelList(context)
    }

    fun loadVModelList(context: Context?) {
        val call = apiInterface?.getVehicleModelListData(vClassValue, vMakeValue)
        call?.enqueue(object : Callback<ArrayList<String>> {
            override fun onResponse(
                call: Call<ArrayList<String>>,
                response: Response<ArrayList<String>>
            ) {
//                Log.d("response", "response" + response.body())
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        stringlist.clear()
                        val vMakeList = ArrayList<String>(response.body())
                        vMakeList.forEach { e ->
                            stringlist.add(e)
                            Log.d("response", "e" + e)
                        }
                        adapter =
                            VAdapter(
                                context,
                                stringlist,
                                this@VehicleModelSelectionFragment,
                            )
                        modelRecyclerView.adapter = adapter
                    }
                }

            }

            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                Log.d("response", "message" + t.message)
            }

        })

    }

    override fun onClickItemListener(position: Int) {
        val bundle = Bundle()
        bundle.putString(Const.VNOKEY, vNoValue)
        bundle.putString(Const.VCLASSKEY, vClassValue)
        bundle.putString(Const.VMAKEKEY, vMakeValue)
        bundle.putString(Const.VMODELKEY, stringlist.get(position))
        navc?.navigate(
            R.id.action_vehicleModelSelectionFragment_to_vehicleFuelTypeSelectionFragment,
            bundle
        )
    }
}