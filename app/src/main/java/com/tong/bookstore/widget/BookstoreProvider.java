package com.tong.bookstore.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.tong.bookstore.R;
import com.tong.bookstore.main.StartActivity;
import com.tong.bookstore.util.Constants;


/**
 * @author tong.zhang
 * @version 1.0.0
 * @date 2016-04-12 19:41
 * @since 5.3.0
 */
public class BookstoreProvider extends AppWidgetProvider {

    private static final String TAG = "BookstoreProvider";

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(TAG, "onUpdate");
        for (int appWidgetId : appWidgetIds) {
            updateTime(context, appWidgetId, appWidgetManager);
        }

    }

    private void updateTime(Context context, int appWidgetId, AppWidgetManager appWidgetManager) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout_book_store);
        remoteViews.setOnClickPendingIntent(R.id.market_btn, getPendingIntent(context));
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    public PendingIntent getPendingIntent(Context context) {
        Intent intent = new Intent(context, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(context, Constants.PENDING_CODE, intent, 0);
    }

    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}
