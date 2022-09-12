package hr.tvz.android.zavrsniprojekt.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.widget.RemoteViews
import hr.tvz.android.zavrsniprojekt.R
import hr.tvz.android.zavrsniprojekt.models.Character

private var name: String = "None selected"
private var game: String = "No game selected"
private var pic: Int = R.drawable.gta

class CharacterWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds)
        updateAppWidget(context, appWidgetManager, appWidgetId)
    }

    fun setLastCharacter(c: Character, context: Context){
        name = c.name
        game = c.game
        pic = c.pic

        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager
            .getAppWidgetIds(ComponentName(context, CharacterWidget::class.java))

        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.character_widget)

    views.setTextViewText(R.id.widgetName, name)
    views.setTextViewText(R.id.widgetGame, game)
    views.setImageViewResource(R.id.widgetImg, pic)

    appWidgetManager.updateAppWidget(appWidgetId, views)
}