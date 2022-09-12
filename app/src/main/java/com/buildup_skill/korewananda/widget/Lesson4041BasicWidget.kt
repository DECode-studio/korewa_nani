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
import com.buildup_skill.korewananda.model.kanjiData_40_41
import com.buildup_skill.korewananda.model.kanji_question

/**
 * Implementation of App Widget functionality.
 */
var taskIndex_4041 : Int = 0
var totalTask_4041 : Int = 0
var textKanji_4041 : String = ""
var answerKanji_4041 : String = ""
var jawaban_1_4041 : String = ""
var jawaban_2_4041 : String = ""
var totalTrue_4041 : Int = 0

lateinit var appWidgetManager_4041: AppWidgetManager
var appWidgetIds_4041: Int? = null

class Lesson4041BasicWidget : AppWidgetProvider() {

    private val btn_answer_1 = "btn_answer_1"
    private val btn_answer_2 = "btn_answer_2"

    var clickButton : Int = 0

    val kanjiDatas: MutableList<kanji_question> = kanjiData_40_41

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
        val views = RemoteViews(context.packageName, R.layout.lesson4041_basic_widget)
        appWidgetManager_4041 = appWidgetManager
        appWidgetIds_4041 = appWidgetId

        getData( views,  kanjiDatas)

        views.setOnClickPendingIntent(R.id.btn_jawaban_1_4041,
            getPendingSelfIntent(context, btn_answer_1, appWidgetId))

        views.setOnClickPendingIntent(R.id.btn_jawaban_2_4041,
            getPendingSelfIntent(context, btn_answer_2, appWidgetId))

        if (clickButton == 1){
            verifyData(context, views, jawaban_1_4041)
        }

        if (clickButton == 2){
            verifyData(context, views, jawaban_2_4041)
        }

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val views = RemoteViews(context!!.packageName, R.layout.lesson4041_basic_widget)

        if (btn_answer_1 == intent!!.action){
            clickButton = 1
            verifyData(context!!, views, jawaban_1_4041)
            appWidgetManager_4041!!.updateAppWidget(appWidgetIds_4041!!, views)
            updateWidgetPictureAndButtonListener(context)
        }

        if (btn_answer_2 == intent!!.action){
            clickButton = 2
            verifyData(context!!, views, jawaban_2_4041)
            appWidgetManager_4041!!.updateAppWidget(appWidgetIds_4041!!, views)
            updateWidgetPictureAndButtonListener(context)
        }
    }

    private fun updateWidgetPictureAndButtonListener(context: Context) {
        val remoteViews = RemoteViews(context.packageName, R.layout.lesson4041_basic_widget)

        val myWidget = ComponentName(context, Lesson4041BasicWidget::class.java)
        val manager = AppWidgetManager.getInstance(context)
        manager.updateAppWidget(myWidget, remoteViews)
    }

    protected fun getPendingSelfIntent(context: Context?, action: String?, appWidgetIds: Int): PendingIntent? {
        val intent = Intent(context, Lesson4041BasicWidget::class.java)
        intent.action = action
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun verifyData(context: Context, views : RemoteViews, selectionAnswer : String) {
        if (selectionAnswer == answerKanji_4041) {
            Toast.makeText(context,"Selamat jawaban anda benar\nJawaban :\n${answerKanji_4041}", Toast.LENGTH_SHORT).show()

            totalTrue_4041+=1

            getData( views,  kanjiDatas)
        } else {
            Toast.makeText(context,"Maaf jawaban anda salah\n" +
                    "Jawaban :\n" +
                    "${answerKanji_4041}", Toast.LENGTH_SHORT).show()

            getData( views,  kanjiDatas)
        }
    }

    private fun getData(views : RemoteViews,  kanjiData : MutableList<kanji_question>) {

        totalTask_4041 = kanjiData.size

        views.setTextViewText(R.id.txt_keterangan_4041, "Jumlah benar : ${totalTrue_4041} / ${totalTask_4041}")

        if (taskIndex_4041 < totalTask_4041) {
            taskIndex_4041+=1
        } else {
            taskIndex_4041 = 0
        }

        for (x in 0 until kanjiData.size - 1){
            if (kanjiData[x].no == taskIndex_4041) {
                var datAnswer = mutableListOf(x,
                    ((0 until (kanjiData.size - 1)).filter {it != x}).random())

                views.setTextViewText(R.id.txt_kanji_4041, kanjiData[x].question)
                answerKanji_4041 = kanjiData[x].answer.toString()

                var data_1 : Int = datAnswer.random()
                views.setTextViewText(R.id.btn_jawaban_1_4041, kanjiData[data_1].answer.toString())
                jawaban_1_4041 = kanjiData[data_1].answer.toString()
                datAnswer.remove(data_1)

                views.setTextViewText(R.id.btn_jawaban_2_4041, kanjiData[datAnswer.random()].answer.toString())
                jawaban_2_4041 = kanjiData[datAnswer.random()].answer.toString()
            }
        }
    }
}