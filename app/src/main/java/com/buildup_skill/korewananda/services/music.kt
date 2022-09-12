package com.buildup_skill.korewananda.services

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer

var sound_voice : Boolean = true

var m = MediaPlayer()

fun playMusic(context : Context, musicMode : Boolean) {
    try {
        //m.isPlaying
        if (musicMode == false) {
            m.stop()
            m.release()
            m = MediaPlayer()
        } else {
            val descriptor: AssetFileDescriptor = context.assets.openFd("mp3/back_sound.mp3")

            m.setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
            descriptor.close()
            m.prepare()
            m.setVolume(1f, 1f)
            m.isLooping = true
            m.start()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}