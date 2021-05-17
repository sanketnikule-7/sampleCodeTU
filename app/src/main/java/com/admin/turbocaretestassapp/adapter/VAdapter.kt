package com.admin.turbocaretestassapp.adapter

import android.content.Context
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.admin.turbocaretestassapp.R
import com.admin.turbocaretestassapp.utils.Listener
import java.util.ArrayList

class VAdapter(
    private val mContext: Context?,
    private val arrayList: ArrayList<String>,
    var mlistener: Listener
) : RecyclerView.Adapter<VAdapter.vViewHolder>() {

    private val context: Context
    var vArrayList: ArrayList<String>? = null
    private val listener: Listener

    init {
        this.context = mContext!!
        this.vArrayList = arrayList
        this.listener = mlistener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VAdapter.vViewHolder {
        val view: View
        view = LayoutInflater.from(parent.context)
            .inflate(R.layout.v_item, parent, false)

        return vViewHolder(view)
    }

    override fun onBindViewHolder(holder: VAdapter.vViewHolder, position: Int) {
        val itemsList: String? = vArrayList?.get(position)
        if (itemsList != null) {
            holder.mVehicleData.setText(itemsList)
        }
    }


    override fun getItemCount(): Int {
        return vArrayList!!.size
    }

    inner class vViewHolder(val containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        var mVehicleData: TextView

        init {
            mVehicleData = itemView.findViewById<View>(R.id.txt_vMakeName) as TextView
            itemView.setOnClickListener {
                listener.onClickItemListener(adapterPosition)
            }
        }
    }
}