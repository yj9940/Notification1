package com.example.lenovo.notification1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotifyService extends Service{
    private NotificationManager nm;
    private boolean isRec = false;
    private boolean isFirst = true;
    private String ACTION = Intent.ACTION_TIME_TICK;
    private SharedPreferences sp;
    private IntentFilter ifter;
    private TimeReceiver receiver;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Service", "onCreate咩");
        sp = getSharedPreferences("UserNote", MODE_PRIVATE);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        ifter = new IntentFilter();
        ifter.addAction(ACTION);
        receiver = new TimeReceiver();
        if (isRec == false) {
            registerReceiver(receiver, ifter);
            isRec = true;
        }
        Log.i("Service", "onCreate娃哈哈");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("拜拜");
        unregisterReceiver(receiver);
    }

    @SuppressWarnings("deprecation")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO 自动生成方法根
        Log.i("Service", "onStart");
        if (isFirst) {
            isFirst = false;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
            String curTime = sdf.format(new Date());
            System.out.println(curTime+"我是不朽的");

            String time =sp.getString("time", "0000");

            System.out.println("curTime"+curTime+"    setTime"+time);
            if (curTime.equals(time)) {
                String text = sp.getString("text", "默认事件");
                System.out.println("onStart");
                Notification notify = new Notification(R.drawable.ic_launcher,
                        "小贴士", System.currentTimeMillis());
                PendingIntent pi = PendingIntent.getActivity(this,0,
                        new Intent(this,MainActivity.class),0);
                notify.setLatestEventInfo(this, "小贴士提醒您", time+"  "+text, pi);
                notify.defaults = Notification.DEFAULT_ALL;
                nm.notify(1044, notify);
                Intent ActIntent = new Intent(this,DialogActivity.class);
                ActIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(ActIntent);

                stopSelf();
            }
        }
        return super.onStartCommand(intent, flags, startId);

    }
}