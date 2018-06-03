package com.highthon.raceofsport

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_extend.*
import org.jetbrains.anko.startActivity

class ExtendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extend)

        btn_extend_enroll.setOnClickListener {
            startActivity<GymSignActivity>()
        }
        btn_extend_reserve.setOnClickListener {
            startActivity<ReserveActivity>()
        }
        btn_extend_reserve_info2.setOnClickListener {
            startActivity<ReservedInfoActivity>()
        }
    }
}
