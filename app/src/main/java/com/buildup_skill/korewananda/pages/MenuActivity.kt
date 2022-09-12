package com.buildup_skill.korewananda.pages

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.buildup_skill.korewananda.R


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val rb_all_lesson : RadioButton = findViewById(R.id.rb_all_lesson)
        val rb_36_37_lesson : RadioButton = findViewById(R.id.rb_36_37_lesson)
        val rb_38_39_lesson : RadioButton = findViewById(R.id.rb_38_39_lesson)
        val rb_40_41_lesson : RadioButton = findViewById(R.id.rb_40_41_lesson)
        val rb_42_43_lesson : RadioButton = findViewById(R.id.rb_42_43_lesson)
        val rb_44_45_lesson : RadioButton = findViewById(R.id.rb_44_45_lesson)
        val btn_terapkan : Button = findViewById(R.id.btn_terapkan)

        btn_terapkan.setOnClickListener {
            if (rb_all_lesson.isChecked) {
                val intent = Intent(baseContext, TypeActivity::class.java)
                intent.putExtra("id_lesson_first", "23")
                intent.putExtra("id_lesson_last", "35")
                startActivity(intent)
            }

            if (rb_36_37_lesson.isChecked) {
                val intent = Intent(baseContext, TypeActivity::class.java)
                intent.putExtra("id_lesson_first", "36")
                intent.putExtra("id_lesson_last", "37")
                startActivity(intent)
            }

            if (rb_38_39_lesson.isChecked) {
                val intent = Intent(baseContext, TypeActivity::class.java)
                intent.putExtra("id_lesson_first", "38")
                intent.putExtra("id_lesson_last", "39")
                startActivity(intent)
            }

            if (rb_40_41_lesson.isChecked) {
                val intent = Intent(baseContext, TypeActivity::class.java)
                intent.putExtra("id_lesson_first", "40")
                intent.putExtra("id_lesson_last", "41")
                startActivity(intent)
            }

            if (rb_42_43_lesson.isChecked) {
                val intent = Intent(baseContext, TypeActivity::class.java)
                intent.putExtra("id_lesson_first", "42")
                intent.putExtra("id_lesson_last", "43")
                startActivity(intent)
            }

            if (rb_44_45_lesson.isChecked) {
                val intent = Intent(baseContext, TypeActivity::class.java)
                intent.putExtra("id_lesson_first", "44")
                intent.putExtra("id_lesson_last", "45")
                startActivity(intent)
            }
        }
    }
}