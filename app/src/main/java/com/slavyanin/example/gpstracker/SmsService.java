package com.slavyanin.example.gpstracker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void showNotification(String text) {
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Context context = getApplicationContext();
        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle("GPS information")
                .setContentText(text)
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.getNotification();
        notificationManager.notify(R.drawable.ic_launcher, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String sms_body = intent.getExtras().getString("sms_body");
        showNotification(sms_body);
        saveSms(sms_body);
        return START_STICKY;
    }

    private void saveSms(String sms_body) {
        Date now = new Date();
        long now_long = now.getTime();

        ContentValues values = new ContentValues();
        values.put(SmsTable.COLUMN_DATE, now_long);
        values.put(SmsTable.COLUMN_TEXT, sms_body);

        getContentResolver().insert(SmsContentProvider.CONTENT_URI, values);
    }

    private GpsData processSms(String sms_body) {
        Pattern pattern = Pattern.compile(
                "lat:(\\d+) " +
                        "long:(\\d+) " +
                        "speed:(\\d+) " +
                        "T:(\\d+)/(\\d+)/(\\d+) (\\d+):(\\d+)" +
                                "http://(.+)");
        if (pattern.matcher(sms_body).matches()) {
            Matcher matcher = pattern.matcher(sms_body);
            GpsData data = new GpsData();
            data.latitude = Double.parseDouble(matcher.group(1));
            data.longtitude = Double.parseDouble(matcher.group(2));
            data.speed = Double.parseDouble(matcher.group(3));
            data.date = matcher.group(5) + " " + checkMonth(Integer.parseInt(matcher.group(4))) + " 20" + matcher.group(6);
            data.time = matcher.group(7) + ":" + matcher.group(8);
            return data;
        }
        return null;
    }

    private String checkMonth(int number) {
        String month = null;
        switch (number) {
            case 1:
                month = "Jan";
                break;
            case 2:
                month = "Feb";
                break;
            case 3:
                month = "Mar";
                break;
            case 4:
                month = "Apr";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "Jun";
                break;
            case 7:
                month = "Jul";
                break;
            case 8:
                month = "Aug";
                break;
            case 9:
                month = "Sep";
                break;
            case 10:
                month = "Oct";
                break;
            case 11:
                month = "Nov";
                break;
            case 12:
                month = "Dec";
                break;
        }
        return month;
    }

}
