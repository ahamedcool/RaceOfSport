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
import org.jetbrains.anko.uiThread

class GymSignActivity : AppCompatActivity() {

    val PREFS_FILENAME = "com.highthon.raceofsport.prefs"
    var prefs: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enroll)

        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)
        val id = prefs!!.getString("ID", "ERROR")
        btn_enroll_do.setOnClickListener {
            val name = edit_enroll_name.text.toString()
            val address = edit_enroll_address.text.toString()
            val price = edit_enroll_price.text.toString()

            doAsync {

                Fuel.post("http://192.168.137.1:8000/api/gym/register", listOf("gymRegion" to name, "gymLocation" to address, "gymPrice" to price, "gymAvailable" to "1", "gymAdmin" to id)).responseString { request, response, result ->
                    run {
                        when (result) {
                            is Result.Failure -> {
                                val ex = result.getException().toString()
                                uiThread{ Toast.makeText(it, ex, Toast.LENGTH_SHORT).show() }
                            }
                            is Result.Success -> {
                                val data= result.get()
                                    startActivity<MainActivity>()
                                uiThread{ Toast.makeText(it, "성공적으로 등록되었습니다.", Toast.LENGTH_SHORT).show()
                                finish()}

                            }
                        }

                    }
                }
            }
        }
    }
}
