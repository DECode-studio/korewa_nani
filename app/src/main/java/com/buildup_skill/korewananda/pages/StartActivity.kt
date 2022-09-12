package com.buildup_skill.korewananda.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.buildup_skill.korewananda.R
import com.buildup_skill.korewananda.services.playMusic
import com.buildup_skill.korewananda.services.sound_voice
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val btn_aktifkan : Button = findViewById(R.id.btn_aktifkan)
        val btn_matikan : Button = findViewById(R.id.btn_matikan)
        val btn_copyright : CircleImageView = findViewById(R.id.btn_copyright)
        val btn_info : CircleImageView = findViewById(R.id.btn_info)
        val btn_sound : CircleImageView = findViewById(R.id.btn_sound)

        btn_matikan.setOnClickListener {
            finishAffinity()
        }

        btn_aktifkan.setOnClickListener {
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        btn_copyright.setOnClickListener {
            showCopyRight()
        }

        btn_info.setOnClickListener {
            showInfo()
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

    private fun showCopyRight() {
        val modalView = LayoutInflater.from(this).inflate(R.layout.copy_right_modal, null)
        val modalBuilder = AlertDialog.Builder(this).setView(modalView)

        val modalInstance = modalBuilder.show()

//        modalView.setOnClickListener(){
//            //close dialog
//            modalInstance.dismiss()
//        }
    }

    private fun showInfo() {
        val modalView = LayoutInflater.from(this).inflate(R.layout.info_modal, null)
        val modalBuilder = AlertDialog.Builder(this).setView(modalView)

        val modalInstance = modalBuilder.show()

//        modalView.setOnClickListener(){
//            //close dialog
//            modalInstance.dismiss()
//        }
    }
}