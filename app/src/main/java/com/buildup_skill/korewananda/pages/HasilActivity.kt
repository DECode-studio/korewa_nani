package com.buildup_skill.korewananda.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.buildup_skill.korewananda.R

class HasilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil)

        val txt_skor : TextView = findViewById(R.id.txt_skor)
        val btn_mulai_baru : Button = findViewById(R.id.btn_mulai_baru)
        val btn_menu_utama : Button = findViewById(R.id.btn_menu_utama)
        val btn_coba_lagi : Button = findViewById(R.id.btn_coba_lagi)

        val id_lesson_first = intent.getStringExtra("id_lesson_first")
        val task_number = intent.getStringExtra("task_number")
        val total_lesson = intent.getStringExtra("total_lesson")
        val total_true = intent.getStringExtra("total_true")
        val page_type = intent.getStringExtra("page_type")

        var score : String = ""

        score = (((total_true!!.toDouble() / total_lesson!!.toDouble()) * 100).toInt()).toString()
        txt_skor.text = score

        btn_menu_utama.setOnClickListener {
            val intent = Intent(baseContext, StartActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        btn_coba_lagi.setOnClickListener {
            if (page_type.toString() == "pilihan") {
                val intent = Intent(baseContext, PilihanActivity::class.java)
                intent.putExtra("id_lesson_first", id_lesson_first)
                intent.putExtra("id_lesson_last", id_lesson_first)
                intent.putExtra("task_number", task_number)
                startActivity(intent)
                finish()
            }

            if (page_type.toString() == "isian") {
                val intent = Intent(baseContext, IsianActivity::class.java)
                intent.putExtra("id_lesson_first", id_lesson_first)
                intent.putExtra("id_lesson_last", id_lesson_first)
                intent.putExtra("task_number", task_number)
                startActivity(intent)
                finish()
            }
        }

        btn_mulai_baru.setOnClickListener {
            val intent = Intent(baseContext, MenuActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}