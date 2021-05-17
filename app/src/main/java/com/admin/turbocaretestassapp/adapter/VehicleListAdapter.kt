package com.admin.turbocaretestassapp.adapter

import android.content.Context
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.admin.turbocaretestassapp.R
import com.admin.turbocaretestassapp.model.VehicleItemModel
import com.admin.turbocaretestassapp.utils.Listener
import java.util.ArrayList

class VehicleListAdapter(
    private val mContext: Context,
    private val arrayList: ArrayList<VehicleItemModel>,
    var mlistener: Listener
) : RecyclerView.Adapter<VehicleListAdapter.vViewHolder>() {

    private val context: Context
    var vArrayList: ArrayList<VehicleItemModel>? = null
    private val listener: Listener

    init {
        this.context = mContext
        this.vArrayList = arrayList
        this.listener = mlistener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VehicleListAdapter.vViewHolder {
        val view: View
        view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vehicle_item, parent, false)

        return vViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleListAdapter.vViewHolder, position: Int) {
        val itemsList: VehicleItemModel? = vArrayList?.get(position)
        if (itemsList != null) {
            holder.mVehicleNo.setText(itemsList.vehicleNo)
            val vData = itemsList.vehicleMake + " " + itemsList.vehicleModel
            holder.mVehicleData.setText(vData)
        }
    }


    override fun getItemCount(): Int {
        return vArrayList!!.size
    }

    fun addNewData(dataItemsList: ArrayList<VehicleItemModel>) {
        arrayList.clear()
        arrayList.addAll(dataItemsList)
        notifyDataSetChanged()
    }


    inner class vViewHolder(val containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        var mVehicleNo: TextView
        var mVehicleData: TextView

        init {
            mVehicleNo = itemView.findViewById<View>(R.id.txt_vehicleNo) as TextView
            mVehicleData = itemView.findViewById<View>(R.id.txt_vData) as TextView
            itemView.setOnClickListener {
                listener.onClickItemListener(adapterPosition)
            }
        }
    }


}