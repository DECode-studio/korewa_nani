package com.buildup_skill.korewananda.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.buildup_skill.korewananda.R
import com.buildup_skill.korewananda.model.*
import com.buildup_skill.korewananda.services.playMusic
import com.buildup_skill.korewananda.services.sound_voice
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class PilihanActivity : AppCompatActivity() {

    lateinit var txt_keterangan : TextView
    lateinit var txt_kanji : TextView
    lateinit var txt_jawaban : TextView
    lateinit var btn_exit : CircleImageView
    lateinit var btn_answer_1 : Button
    lateinit var btn_answer_2 : Button
    lateinit var btn_answer_3 : Button

    var taskIndex : Int = 0
    var totalTask : Int = 0
    var answerKanji : String = ""
    var totalTrue : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilihan)

        txt_keterangan = findViewById(R.id.txt_keterangan)
        txt_kanji = findViewById(R.id.txt_kanji)
        txt_jawaban = findViewById(R.id.txt_jawaban)
        btn_exit = findViewById(R.id.btn_exit)
        btn_answer_1 = findViewById(R.id.btn_answer_1)
        btn_answer_2 = findViewById(R.id.btn_answer_2)
        btn_answer_3 = findViewById(R.id.btn_answer_3)
        val btn_sound : CircleImageView = findViewById(R.id.btn_sound)

        val id_lesson_first = intent.getStringExtra("id_lesson_first")
        val id_lesson_last = intent.getStringExtra("id_lesson_last")
        val task_number = intent.getStringExtra("task_number")

        if (answerKanji == "") {

            if (task_number != null) {
                getKanji(id_lesson_first.toString(),task_number.toDouble()/100)
            }
        }

        btn_exit.setOnClickListener {
            onBackPressed()
        }

        btn_answer_1.setOnClickListener {
            verifyData(btn_answer_1.text.toString(), id_lesson_first.toString(), task_number.toString())
        }

        btn_answer_2.setOnClickListener {
            verifyData(btn_answer_2.text.toString(), id_lesson_first.toString(), task_number.toString())
        }

        btn_answer_3.setOnClickListener {
            verifyData(btn_answer_3.text.toString(), id_lesson_first.toString(), task_number.toString())
        }

        btn_sound.setOnClickListener {

            if (sound_voice == true) {
                Glide
                    .with(this)
                    .load(R.drawable.ic_sound_off)
                    .into(btn_sound)

                sound_voice = false

                playMusic(this, sound_voice)
            } else {
                Glide
                    .with(this)
                    .load(R.drawable.ic_sound_on)
                    .into(btn_sound)

                sound_voice = true

                playMusic(this, sound_voice)
            }

        }

        if (sound_voice == true) {
            playMusic(this, sound_voice)
            Glide
                .with(this)
                .load(R.drawable.ic_sound_on)
                .into(btn_sound)
        } else {
            playMusic(this, sound_voice)
            Glide
                .with(this)
                .load(R.drawable.ic_sound_off)
                .into(btn_sound)
        }

    }

    private fun verifyData(selectionAnswer : String, id_lesson_first: String, task_number: String) {
        if (taskIndex == totalTask) {
            if (selectionAnswer == answerKanji) {
                Toast.makeText(this,"Selamat jawaban anda benar", Toast.LENGTH_SHORT).show()

                txt_jawaban.visibility = View.VISIBLE
                totalTrue+=1

                Handler().postDelayed({

                    toResult(id_lesson_first, task_number)
                }, 2000)
            } else {
                Toast.makeText(this,"Maaf jawaban anda salah", Toast.LENGTH_SHORT).show()

                txt_jawaban.visibility = View.VISIBLE

                Handler().postDelayed({

                    toResult(id_lesson_first, task_number)
                }, 2000)
            }
        } else {
            if (selectionAnswer == answerKanji) {
                Toast.makeText(this,"Selamat jawaban anda benar", Toast.LENGTH_SHORT).show()

                txt_jawaban.visibility = View.VISIBLE
                totalTrue+=1

                Handler().postDelayed({
                    if (task_number != null) {
                        getKanji(id_lesson_first.toString(),task_number.toDouble()/100)
                    }
                }, 2000)
            } else {
                Toast.makeText(this,"Maaf jawaban anda salah", Toast.LENGTH_SHORT).show()

                txt_jawaban.visibility = View.VISIBLE

                Handler().postDelayed({
                    if (task_number != null) {
                        getKanji(id_lesson_first.toString(),task_number.toDouble()/100)
                    }
                }, 2000)
            }
        }
    }

    private fun toResult(id_lesson_first: String, task_number: String) {
        val intent = Intent(baseContext, HasilActivity::class.java)
        intent.putExtra("id_lesson_first", id_lesson_first)
        intent.putExtra("task_number", task_number)
        intent.putExtra("total_lesson", totalTask.toString())
        intent.putExtra("total_true", totalTrue.toString())
        intent.putExtra("page_type", "pilihan")
        startActivity(intent)
        finish()
    }

    private fun getKanji(id_lesson_first : String, size_data : Double){//(id_lesson_first : Int, id_lesson_last : Int, size_data : Int) {
        if (id_lesson_first == "23") {
            getData(size_data, kanjiData_all)
        }

        if (id_lesson_first == "36") {
            getData(size_data, kanjiData_36_37)
        }

        if (id_lesson_first == "38") {
            getData(size_data, kanjiData_38_39)
        }

        if (id_lesson_first == "40") {
            getData(size_data, kanjiData_40_41)
        }

        if (id_lesson_first == "42") {
            getData(size_data, kanjiData_42_43)
        }

        if (id_lesson_first == "44") {
            getData(size_data, kanjiData_44_45)
        }
    }

    private fun getData(size_data : Double, kanjiData: MutableList<kanji_question>) {
        txt_jawaban.visibility = View.GONE

        totalTask = (kanjiData.size * size_data).toInt()

        if (taskIndex < totalTask) {
            taskIndex+=1
        }

        for (x in 0 until kanjiData.size - 1){
            if (kanjiData[x].no == taskIndex) {
                var datAnswer = mutableListOf(x,
                    ((0 until (kanjiData.size - 1)).filter {it != x}).random(),
                    ((0 until (kanjiData.size - 1)).filter {it != x}).random())

                txt_kanji.text = kanjiData[x].question
                answerKanji = kanjiData[x].answer.toString()
                txt_jawaban.text = "Jawaban :\n${answerKanji}"

                var data_1 : Int = datAnswer.random()
                btn_answer_1.text = kanjiData[data_1].answer.toString()
                datAnswer.remove(data_1)

                var data_2 : Int = datAnswer.random()
                btn_answer_2.text = kanjiData[data_2].answer.toString()
                datAnswer.remove(data_2)

                btn_answer_3.text = kanjiData[datAnswer.random()].answer.toString()
            }
        }

        txt_keterangan.text = "Soal ke : ${taskIndex}/${totalTask}"
    }
}