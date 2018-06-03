package com.highthon.raceofsport

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_my_reserve.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ReservedInfoActivity : AppCompatActivity(), OnItemClickListener{
    override fun onItemClick(position: Int) {

    }
    val PREFS_FILENAME = "com.highthon.raceofsport.prefs"
    var prefs: SharedPreferences? = null
    private var adapterGym: RecyclerView.Adapter<*>? = null
    private val mItemsGym = ArrayList<ReserveAdapter.ReservedItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_reserve)

        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)

        reserve_info.layoutManager = LinearLayoutManager(this)
        reserve_info.setHasFixedSize(false)
        adapterGym = ReserveAdapter(mItemsGym, this)
        reserve_info.adapter = adapterGym
        getGymList()

    }

    private fun getGymList() {
        doAsync {

            try {
                mItemsGym.add(ReserveAdapter.ReservedItem(prefs!!.getString("name", ""),"날짜 : "+prefs!!.getString("date", ""),"시각 : "+prefs!!.getString("time", ""), "전화번호 : " + prefs!!.getString("phone", "")))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            uiThread{
                it.adapterGym!!.notifyDataSetChanged()
            }
        }
    }
}
