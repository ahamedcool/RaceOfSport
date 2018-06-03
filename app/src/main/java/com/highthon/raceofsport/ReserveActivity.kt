package com.highthon.raceofsport

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_reserve.*
import android.app.TimePickerDialog
import java.util.*
import android.app.DatePickerDialog
import android.content.SharedPreferences
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity


class ReserveActivity : AppCompatActivity() {

    val PREFS_FILENAME = "com.highthon.raceofsport.prefs"
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)

        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)

        btn_reverse.setOnClickListener {
            val name = edit_name.text.toString()
            val date = text_date.text.toString()
            val time = text_time.text.toString()
            val phone = edit_phone.text.toString()

            doAsync {

                try {
                    val editor = prefs!!.edit()
                    editor.putString("name", name)
                    editor.putString("date", date)
                    editor.putString("time", time)
                    editor.putString("phone", phone)
                    editor.apply()

                    startActivity<ReservedInfoActivity>()
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }
    fun date(v: View?){
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
