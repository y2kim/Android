package exam.android.kh.notificationexam;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.util.Date;

public class NotificationListener extends NotificationListenerService {
    //알림 오는것

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
      //  if(sbn.getPackageName().equals("com.kakao.talk")){ } // 없으면 모든 알림됨
        //올때
        Notification noti = sbn.getNotification();
        Bundle bundle = noti.extras;
        //Bundle 꾸러미
        String title = bundle.getString(Notification.EXTRA_TITLE);
        String text = bundle.getString(Notification.EXTRA_TEXT);
        System.out.println(new Date(sbn.getPostTime()));
        System.out.println(title +" : " + text);
        Log.e("!!!",title+text);
        Log.e("!!!", String.valueOf(new Date(sbn.getPostTime())));
        //서비스 백그라운드에서 돌아감
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

        //제거
    }

}
