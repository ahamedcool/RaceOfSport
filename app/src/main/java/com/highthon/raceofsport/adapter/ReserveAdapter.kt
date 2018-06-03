package com.highthon.raceofsport

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.reserved_gym_item.view.*
import java.util.ArrayList

/**
* Created by hyunjin on 2018. 5. 11..
*/
class ReserveAdapter(private var mItems: ArrayList<ReservedItem>, listener : OnItemClickListener) : RecyclerView.Adapter<ReserveAdapter.ItemViewHolder>() {

    val listeners : OnItemClickListener = listener

    // 새로운 뷰 홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reserved_gym_item, parent, false)

        return ItemViewHolder(view)
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.name.text = mItems[position].name
        holder.start.text = mItems[position].start
        holder.end.text = mItems[position].end
        holder.allow.text = mItems[position].allow
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
        val name: TextView = itemView.title
        val start: TextView = itemView.start_reverse
        val end: TextView = itemView.end_reverse
        val allow: TextView = itemView.allow_check
    }
    class ReservedItem(val name: String, val start: String, val end: String, val allow: String)
}