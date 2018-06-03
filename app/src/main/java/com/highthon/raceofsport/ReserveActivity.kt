package com.highthon.raceofsport

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_reserve.*
import android.widget.TimePicker
import android.app.TimePickerDialog
import java.util.*
import android.app.DatePickerDialog
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class ReserveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)

        btn_reverse.setOnClickListener {
            val name = edit_name.text.toString()
            val date = text_date.text.toString()
            val time = text_time.text.toString()
            val phone = edit_phone.text.toString()

            doAsync {

                try {
                    /* Fuel.post("http://192.168.137.1:8000/api/appoint/register", listOf("user_id" to id, "user_password" to pw)).response { request, response, result ->
                        run {
                                when (result) {
                                    is Result.Failure -> {
                                        val ex = result.getException().toString()
                                        uiThread{ Toast.makeText(it, ex, Toast.LENGTH_SHORT).show() }
                                    }
                                    is Result.Success -> {
                                        val data= result.get()

                                        uiThread{ Toast.makeText(it, "예약이 완료되었습니다", Toast.LENGTH_SHORT).show() }

                                        finish()
                                    }
                                }

                        }
                    } */
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }
    public fun date(v: View?){
        val c : Calendar= Calendar.getInstance()
        val dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val month_c = month+1

            text_date.text = "$year-$month_c-$dayOfMonth"
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))

        dialog.show()
    }

    fun time(v: View?){
        TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, i, i1 ->
            val hour = timePicker.hour.toString() // == i
            val minute = timePicker.minute.toString() // == i1
            text_time.text = "$hour : $minute"
        }, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true).show()
    }
}
