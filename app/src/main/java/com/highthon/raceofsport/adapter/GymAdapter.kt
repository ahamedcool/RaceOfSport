package com.highthon.raceofsport

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_reserve.view.*
import java.util.ArrayList

/**
* Created by hyunjin on 2018. 5. 11..
*/
class GymAdapter(private var mItems: ArrayList<RecyclerItem>, listener : OnItemClickListener) : RecyclerView.Adapter<GymAdapter.ItemViewHolder>() {

    val listeners : OnItemClickListener = listener

    // 새로운 뷰 홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gym, parent, false)

        return ItemViewHolder(view)
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.appName.text = mItems[position].name
    }

    // 데이터 셋의 크기를 리턴
    override fun getItemCount(): Int {
        return mItems.size
    }

    // 커스텀 뷰홀더
    // binding widgets on item layout
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            listeners.onItemClick(layoutPosition)
        }
        val appName: TextView = itemView.title

    }
}

class RecyclerItem(val name: String)