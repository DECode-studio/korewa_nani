package com.buildup_skill.korewananda.pages

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.buildup_skill.korewananda.R

class TypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type)

        val rb_pilihan_ganda : RadioButton = findViewById(R.id.rb_pilihan_ganda)
        val rb_isian : RadioButton = findViewById(R.id.rb_isian)
        val btn_terapkan : Button = findViewById(R.id.btn_terapkan)

        val id_lesson_first = intent.getStringExtra("id_lesson_first")
        val id_lesson_last = intent.getStringExtra("id_lesson_last")

        btn_terapkan.setOnClickListener {
            if (rb_pilihan_ganda.isChecked) {
                val intent = Intent(baseContext, JumlahActivity::class.java)
                intent.putExtra("id_lesson_first", id_lesson_first)
                intent.putExtra("id_lesson_last", id_lesson_last)
                intent.putExtra("page_type", "pilihan")
                startActivity(intent)
            }

            if (rb_isian.isChecked) {
                val intent = Intent(baseContext, JumlahActivity::class.java)
                intent.putExtra("id_lesson_first", id_lesson_first)
                intent.putExtra("id_lesson_last", id_lesson_last)
                intent.putExtra("page_type", "isian")
                startActivity(intent)
            }
        }
    }
}