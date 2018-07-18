package com.test.peterphchen.junittest;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.Random;

public class appWidget extends AppWidgetProvider {
    private ArrayList<String> equations;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName me = new ComponentName(context,appWidget.class);
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        String[] projection = {
                "ROWID AS _id",
                EquationContract.ID,
                EquationContract.TABLE_NAME
        };
        Cursor cursor = db.query(
                EquationContract.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                EquationContract.ID
        );
        equations = new ArrayList<String>();
        while(cursor.moveToNext()){
            equations.add(cursor.getString(2));
        }
        cursor.close();
        db.close();
        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.appwidget_layout);
        int limit = equations.size();
        views.setTextViewText(R.id.widgetText1, equations.get(new Random().nextInt(limit)));
        views.setTextViewText(R.id.widgetText2,equations.get(new Random().nextInt(limit)));
        Intent intent = new Intent(context,appWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,appWidgetIds);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context
                ,0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widgetText1,pendingIntent);
        views.setOnClickPendingIntent(R.id.widgetText2,pendingIntent);
        appWidgetManager.updateAppWidget(me,views);
    }
}
