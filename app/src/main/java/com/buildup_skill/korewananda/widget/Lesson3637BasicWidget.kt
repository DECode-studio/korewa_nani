package com.buildup_skill.korewananda.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast
import com.buildup_skill.korewananda.R
import com.buildup_skill.korewananda.model.kanjiData_36_37
import com.buildup_skill.korewananda.model.kanji_question

/**
 * Implementation of App Widget functionality.
 */

var taskIndex_3637 : Int = 0
var totalTask_3637 : Int = 0
var textKanji_3637 : String = ""
var answerKanji_3637 : String = ""
var jawaban_1_3637 : String = ""
var jawaban_2_3637 : String = ""
var totalTrue_3637 : Int = 0

lateinit var appWidgetManager_3637: AppWidgetManager
var appWidgetIds_3637: Int? = null

class Lesson3637BasicWidget : AppWidgetProvider() {

    private val btn_answer_1 = "btn_answer_1"
    private val btn_answer_2 = "btn_answer_2"

    var clickButton : Int = 0

    val kanjiDatas: MutableList<kanji_question> = kanjiData_36_37

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val views = RemoteViews(context.packageName, R.layout.lesson3637_basic_widget)
        appWidgetManager_3637 = appWidgetManager
        appWidgetIds_3637 = appWidgetId

        getData( views,  kanjiDatas)

        views.setOnClickPendingIntent(R.id.btn_jawaban_1_3637,
            getPendingSelfIntent(context, btn_answer_1, appWidgetId))

        views.setOnClickPendingIntent(R.id.btn_jawaban_2_3637,
            getPendingSelfIntent(context, btn_answer_2, appWidgetId))

        if (clickButton == 1){
            verifyData(context, views, jawaban_1_3637)
        }

        if (clickButton == 2){
            verifyData(context, views, jawaban_2_3637)
        }

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val views = RemoteViews(context!!.packageName, R.layout.lesson3637_basic_widget)

        if (btn_answer_1 == intent!!.action){
            clickButton = 1
            verifyData(context!!, views, jawaban_1_3637)
            appWidgetManager_3637!!.updateAppWidget(appWidgetIds_3637!!, views)
            updateWidgetPictureAndButtonListener(context)
        }

        if (btn_answer_2 == intent!!.action){
            clickButton = 2
            verifyData(context!!, views, jawaban_2_3637)
            appWidgetManager_3637!!.updateAppWidget(appWidgetIds_3637!!, views)
            updateWidgetPictureAndButtonListener(context)
        }
    }

    private fun updateWidgetPictureAndButtonListener(context: Context) {
        val remoteViews = RemoteViews(context.packageName, R.layout.lesson3637_basic_widget)

        val myWidget = ComponentName(context, Lesson3637BasicWidget::class.java)
        val manager = AppWidgetManager.getInstance(context)
        manager.updateAppWidget(myWidget, remoteViews)
    }

    protected fun getPendingSelfIntent(context: Context?, action: String?, appWidgetIds: Int): PendingIntent? {
        val intent = Intent(context, Lesson3637BasicWidget::class.java)
        intent.action = action
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun verifyData(context: Context, views : RemoteViews, selectionAnswer : String) {
        if (selectionAnswer == answerKanji_3637) {
            Toast.makeText(context,"Selamat jawaban anda benar\nJawaban :\n${answerKanji_3637}", Toast.LENGTH_SHORT).show()

            totalTrue_3637+=1

            getData( views,  kanjiDatas)
        } else {
            Toast.makeText(context,"Maaf jawaban anda salah\n" +
                    "Jawaban :\n" +
                    "${answerKanji_3637}", Toast.LENGTH_SHORT).show()

            getData( views,  kanjiDatas)
        }
    }

    private fun getData(views : RemoteViews,  kanjiData : MutableList<kanji_question>) {

        totalTask_3637 = kanjiData.size

        views.setTextViewText(R.id.txt_keterangan_3637, "Jumlah benar : ${totalTrue_3637} / ${totalTask_3637}")

        if (taskIndex_3637 < totalTask_3637) {
            taskIndex_3637+=1
        } else {
            taskIndex_3637 = 0
        }

        for (x in 0 until kanjiData.size - 1){
            if (kanjiData[x].no == taskIndex_3637) {
                var datAnswer = mutableListOf(x,
                    ((0 until (kanjiData.size - 1)).filter {it != x}).random())

                views.setTextViewText(R.id.txt_kanji_3637, kanjiData[x].question)
                answerKanji_3637 = kanjiData[x].answer.toString()

                var data_1 : Int = datAnswer.random()
                views.setTextViewText(R.id.btn_jawaban_1_3637, kanjiData[data_1].answer.toString())
                jawaban_1_3637 = kanjiData[data_1].answer.toString()
                datAnswer.remove(data_1)

                views.setTextViewText(R.id.btn_jawaban_2_3637, kanjiData[datAnswer.random()].answer.toString())
                jawaban_2_3637 = kanjiData[datAnswer.random()].answer.toString()
            }
        }
    }
}