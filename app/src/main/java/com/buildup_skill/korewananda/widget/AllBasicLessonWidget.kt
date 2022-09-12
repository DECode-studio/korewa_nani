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
import com.buildup_skill.korewananda.model.kanjiData_all
import com.buildup_skill.korewananda.model.kanji_question


/**
 * Implementation of App Widget functionality.
 */

var taskIndex : Int = 0
var totalTask : Int = 0
var textKanji : String = ""
var answerKanji : String = ""
var jawaban_1 : String = ""
var jawaban_2 : String = ""
var totalTrue : Int = 0

lateinit var appWidgetManager_2: AppWidgetManager
var appWidgetIds_2: Int? = null

class PilihanWidget : AppWidgetProvider() {

    private val btn_answer_1 = "btn_answer_1"
    private val btn_answer_2 = "btn_answer_2"

    var clickButton : Int = 0

    val kanjiDatas: MutableList<kanji_question> = kanjiData_all

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val views = RemoteViews(context.packageName, R.layout.all_basic_lesson_widget)
        appWidgetManager_2 = appWidgetManager
        appWidgetIds_2 = appWidgetId

        getData( views,  kanjiDatas)

        views.setOnClickPendingIntent(R.id.btn_jawaban_1,
            getPendingSelfIntent(context, btn_answer_1, appWidgetId))

        views.setOnClickPendingIntent(R.id.btn_jawaban_2,
            getPendingSelfIntent(context, btn_answer_2, appWidgetId))

        if (clickButton == 1){
            verifyData(context, views, jawaban_1)
        }

        if (clickButton == 2){
            verifyData(context, views, jawaban_2)
        }

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val views = RemoteViews(context!!.packageName, R.layout.all_basic_lesson_widget)

        if (btn_answer_1 == intent!!.action){
            clickButton = 1
            verifyData(context!!, views, jawaban_1)
            appWidgetManager_2!!.updateAppWidget(appWidgetIds_2!!, views)
            updateWidgetPictureAndButtonListener(context)
        }

        if (btn_answer_2 == intent!!.action){
            clickButton = 2
            verifyData(context!!, views, jawaban_2)
            appWidgetManager_2!!.updateAppWidget(appWidgetIds_2!!, views)
            updateWidgetPictureAndButtonListener(context)
        }
    }

    private fun updateWidgetPictureAndButtonListener(context: Context) {
        val remoteViews = RemoteViews(context.packageName, R.layout.all_basic_lesson_widget)

        val myWidget = ComponentName(context, PilihanWidget::class.java)
        val manager = AppWidgetManager.getInstance(context)
        manager.updateAppWidget(myWidget, remoteViews)
    }

    protected fun getPendingSelfIntent(context: Context?, action: String?, appWidgetIds: Int): PendingIntent? {
        val intent = Intent(context, PilihanWidget::class.java)
        intent.action = action
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun verifyData(context: Context, views : RemoteViews, selectionAnswer : String) {
        if (selectionAnswer == answerKanji) {
            Toast.makeText(context,"Selamat jawaban anda benar\nJawaban :\n${answerKanji}", Toast.LENGTH_SHORT).show()

            totalTrue+=1

            getData( views,  kanjiDatas)
        } else {
            Toast.makeText(context,"Maaf jawaban anda salah\n" +
                    "Jawaban :\n" +
                    "${answerKanji}", Toast.LENGTH_SHORT).show()

            getData( views,  kanjiDatas)
        }
    }

    private fun getData(views : RemoteViews,  kanjiData : MutableList<kanji_question>) {

        totalTask = kanjiData.size

        views.setTextViewText(R.id.txt_keterangan, "Jumlah benar : ${totalTrue} / ${totalTask}")

        if (taskIndex < totalTask) {
            taskIndex+=1
        } else {
            taskIndex = 0
        }

        for (x in 0 until kanjiData.size - 1){
            if (kanjiData[x].no == taskIndex) {
                var datAnswer = mutableListOf(x,
                    ((0 until (kanjiData.size - 1)).filter {it != x}).random())

                views.setTextViewText(R.id.txt_kanji, kanjiData[x].question)
                answerKanji = kanjiData[x].answer.toString()

                var data_1 : Int = datAnswer.random()
                views.setTextViewText(R.id.btn_jawaban_1, kanjiData[data_1].answer.toString())
                jawaban_1 = kanjiData[data_1].answer.toString()
                datAnswer.remove(data_1)

                views.setTextViewText(R.id.btn_jawaban_2, kanjiData[datAnswer.random()].answer.toString())
                jawaban_2 = kanjiData[datAnswer.random()].answer.toString()
            }
        }
    }
}