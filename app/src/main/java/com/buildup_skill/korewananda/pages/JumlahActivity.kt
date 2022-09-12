package com.buildup_skill.korewananda.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import com.buildup_skill.korewananda.R

class JumlahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jumlah)

        val btn_terapkan : Button = findViewById(R.id.btn_terapkan)
        val rb_25 : RadioButton = findViewById(R.id.rb_25)
        val rb_50 : RadioButton = findViewById(R.id.rb_50)
        val rb_75 : RadioButton = findViewById(R.id.rb_75)
        val rb_100 : RadioButton = findViewById(R.id.rb_100)

        val id_lesson_first = intent.getStringExtra("id_lesson_first")
        val id_lesson_last = intent.getStringExtra("id_lesson_last")
        val page_type = intent.getStringExtra("page_type")

        btn_terapkan.setOnClickListener {
            if (page_type == "pilihan"){
                if (rb_25.isChecked) {
                    showPilihan(id_lesson_first.toString(), id_lesson_last.toString(), "25")
                } else if (rb_50.isChecked) {
                    showPilihan(id_lesson_first.toString(), id_lesson_last.toString(), "50")
                } else if (rb_75.isChecked) {
                    showPilihan(id_lesson_first.toString(), id_lesson_last.toString(), "75")
                } else if (rb_100.isChecked) {
                    showPilihan(id_lesson_first.toString(), id_lesson_last.toString(), "100")
                }
            } else if (page_type == "isian"){
                if (rb_25.isChecked) {
                    showIsian(id_lesson_first.toString(), id_lesson_last.toString(), "25")
                }
                if (rb_50.isChecked) {
                    showIsian(id_lesson_first.toString(), id_lesson_last.toString(), "50")
                }
                if (rb_75.isChecked) {
                    showIsian(id_lesson_first.toString(), id_lesson_last.toString(), "75")
                }
                if (rb_100.isChecked) {
                    showIsian(id_lesson_first.toString(), id_lesson_last.toString(), "100")
                }
            }
        }
    }

    private fun showPilihan(id_lesson_first : String, id_lesson_last : String, task_number : String) {
        val intent = Intent(baseContext, PilihanActivity::class.java)
        intent.putExtra("id_lesson_first", id_lesson_first)
        intent.putExtra("id_lesson_last", id_lesson_last)
        intent.putExtra("task_number", task_number)
        startActivity(intent)
    }

    private fun showIsian(id_lesson_first : String, id_lesson_last : String, task_number : String) {
        val intent = Intent(baseContext, IsianActivity::class.java)
        intent.putExtra("id_lesson_first", id_lesson_first)
        intent.putExtra("id_lesson_last", id_lesson_last)
        intent.putExtra("task_number", task_number)
        startActivity(intent)
    }
}