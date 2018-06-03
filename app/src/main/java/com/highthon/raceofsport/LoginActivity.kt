package com.highthon.raceofsport

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync

class LoginActivity : AppCompatActivity() {

    val PREFS_FILENAME = "com.highthon.raceofsport.prefs"
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)
        btn_login_two.setOnClickListener {
            val id = edit_login_id.text.toString()
            val pw = edit_login_pw.text.toString()
            doAsync {


            }
        }
    }
}

