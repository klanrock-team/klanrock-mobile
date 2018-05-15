package id.ac.poljie.android.belajar.klanrockstudio;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnShowNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi component
        btnShowNotif = (Button) findViewById(R.id.btnShowNotif);
        btnShowNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotif();
            }
        });
    }

    private void showNotif() {
        NotificationManager notificationManager;

        Intent mIntent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fromnotif", "notif");
        mIntent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setColor(getResources().getColor(R.color.colorAccent));
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_alarm_black_24dp))
                .setTicker("notif starting")
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("Notification Android")
                .setContentText("by imamfarisi.com");

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(115, builder.build());
    }
}