package com.tong.bookstore;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

/**
 * @author tong.zhang
 * @version 1.0.0
 * @date 2016-04-12 14:53
 * @since 5.3.0
 */
public class BookStoreNotification {

    private BookStoreNotification() {
    }


    public static void notify(Context context, String title, String content) {
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context);

        notifyBuilder.setContentTitle(title);
        notifyBuilder.setTicker(title);
        notifyBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notifyBuilder.setAutoCancel(true);
        StringBuilder append = new StringBuilder().append(content).append(title);
        notifyBuilder.setContentText(append);

        Notification notification = notifyBuilder.build();
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify((int) SystemClock.currentThreadTimeMillis(), notification);
    }

    public static void cancelAll(Context context) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
    }

}
