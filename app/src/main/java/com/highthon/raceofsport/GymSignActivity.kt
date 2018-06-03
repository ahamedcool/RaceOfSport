package com.highthon.raceofsport

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_enroll.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class GymSignActivity : AppCompatActivity() {

    val PREFS_FILENAME = "com.highthon.raceofsport.prefs"
    var prefs: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enroll)

        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)

        btn_enroll_do.setOnClickListener {
            val name = edit_enroll_name.text.toString()
            val address = edit_enroll_address.text.toString()
            val price = edit_enroll_price.text.toString()

            val editor = prefs!!.edit()
            editor.putString("name_gym", name)
            editor.putString("address", address)
            editor.putString("price", price)
            editor.apply()
            toast("등록완료")
            finish()
        }
    }
}
