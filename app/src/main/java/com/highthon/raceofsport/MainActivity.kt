package com.highthon.raceofsport

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), OnItemClickListener{

    override fun onItemClick(position: Int) {

    }

    private var adapterGym: RecyclerView.Adapter<*>? = null
    private val mItemsGym = ArrayList<RecyclerItem>()

    private var adapterReserve: RecyclerView.Adapter<*>? = null
    private val mItemsReserve = ArrayList<RecyclerItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        list_gym.layoutManager = LinearLayoutManager(this)
        list_gym.setHasFixedSize(false)
        adapterGym = GymAdapter(mItemsGym, this)
        list_gym.adapter = adapterGym
        getGymList()

        /*list_reserve.layoutManager = LinearLayoutManager(this)
        list_reserve.setHasFixedSize(false)
        adapterReserve = GymAdapter(mItemsReserve, this)
        list_reserve.adapter = adapterReserve
        getReserveList()*/

    }

    private fun getGymList() {
        doAsync {

            try {
                Fuel.get("http://192.168.137.1:8000/api/gym/available").responseJson { request, response, result ->
                    run {
                        result.fold(success = { json ->
                            uiThread{
                                Toast.makeText(it,json.array().toString(),Toast.LENGTH_SHORT).show()
                            }
                        }, failure = { error ->
                            uiThread{
                                Toast.makeText(it,error.toString(),Toast.LENGTH_SHORT).show()

                            }
                        })

                    }
                }
                mItemsGym.add(RecyclerItem("A"))
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
