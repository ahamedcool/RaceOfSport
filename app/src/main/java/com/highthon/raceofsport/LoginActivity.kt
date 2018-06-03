package com.highthon.raceofsport

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

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
                /*Fuel.post("http://192.168.137.1:8000/api/user/login", listOf("user_id" to id, "user_password" to pw)).responseString { request, response, result ->
                    run {
                        when (result) {
                        is Result.Failure -> {
                            val ex = result.getException().toString()
                            uiThread{ Toast.makeText(it, ex, Toast.LENGTH_SHORT).show() }
                    }
                        is Result.Success -> {
                            val data= result.get()
                            if(data.contains("되었")) {
                                val editor = prefs!!.edit()
                                editor.putString("ID", id)
                                editor.apply()
                                startActivity<MainActivity>()
                                finish()
                            }
                            uiThread{ Toast.makeText(it, data, Toast.LENGTH_SHORT).show() }

                        }
                    }

                    }*/

            }
        }
    }
}

