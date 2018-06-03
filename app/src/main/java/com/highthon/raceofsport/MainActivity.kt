package com.highthon.raceofsport

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread





class MainActivity : AppCompatActivity(), OnItemClickListener{

    override fun onItemClick(position: Int) {

    }

    private var adapterGym: RecyclerView.Adapter<*>? = null
    private var mItemsGym = ArrayList<RecyclerItem>()

    private var adapterReserve: RecyclerView.Adapter<*>? = null
    private val mItemsReserve = ArrayList<RecyclerItem>()


    val PREFS_FILENAME = "com.highthon.raceofsport.prefs"
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)

        list_gym.layoutManager = LinearLayoutManager(this)
        list_gym.setHasFixedSize(false)
        adapterGym = GymAdapter(mItemsGym, this)
        list_gym.adapter = adapterGym
        getGymList()

        title_actionbar.title = "김건님, 안녕하세요!"

    }

    private fun getGymList() {
        doAsync {
            try {
                mItemsGym.add(RecyclerItem("장충 체육관"))
                mItemsGym.add(RecyclerItem("이근식복싱 체육관"))
                mItemsGym.add(RecyclerItem("경희 태권도 체육관"))
                val id : String? = prefs!!.getString("gym_name", null)
                if(id!=null){
                    mItemsGym.add(RecyclerItem(id))
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            uiThread{
                it.adapterGym!!.notifyDataSetChanged()
            }
        }
    }

    private fun getReserveList() {
        doAsync {

            try {
                mItemsReserve.add(RecyclerItem("A"))
                mItemsReserve.add(RecyclerItem("C"))
                mItemsReserve.add(RecyclerItem("B"))
                mItemsReserve.add(RecyclerItem("W"))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            uiThread{
                it.adapterReserve!!.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu, this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_need -> startActivity<ExtendActivity>()
        }
        return false
    }
}
