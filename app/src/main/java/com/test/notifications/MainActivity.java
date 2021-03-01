package com.test.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText duree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.b1).setOnClickListener(this);
        findViewById(R.id.b2).setOnClickListener(this);
        findViewById(R.id.b3).setOnClickListener(this);
        findViewById(R.id.b4).setOnClickListener(this);
        findViewById(R.id.b5).setOnClickListener(this);
        findViewById(R.id.b6).setOnClickListener(this);
        findViewById(R.id.b7).setOnClickListener(this);
        duree=findViewById(R.id.durre);
        //this function will create a notification channel for the first install of the application
        createChannel();
    }
    private void SendNotification() {
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder b = new
                NotificationCompat.Builder(this,"1");
        b.setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setTicker("Formation Android")
                .setContentTitle("Notification")
                .setContentText("bonjour mes collegues je suis heureux d'etre avec vous :).")
                .setContentInfo("INFO")
        .setContentIntent(pendingIntent);
        NotificationManager nm = (NotificationManager)
                this.getSystemService(this.NOTIFICATION_SERVICE);
        nm.notify(1, b.build());
    }
    public void createChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel =new NotificationChannel("1","small channel",NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("this channel is just for testing");
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b1:
                Toast.makeText(getApplicationContext()," Message durée limitée",Toast.LENGTH_LONG).show();
                break;
            case R.id.b2:
                Toast.makeText(getApplicationContext(), " Message durée limitée",Toast.LENGTH_SHORT).show();

                break;
            case R.id.b3:
                final Toast msg = Toast.makeText(getApplicationContext(), " Message durée limitée", Toast.LENGTH_LONG);
                msg.show();
                Handler hd = new Handler();
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        msg.cancel();
                    }
                }, Integer.parseInt(duree.getText().toString()) * 1000);

                break;
            case R.id.b4:
                SendNotification();
                break;
            case R.id.b5:
                AlertDialog.Builder adb = new AlertDialog.Builder(this);
                adb.setMessage("Message");
                adb.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int
                                    arg1) {
                                Toast.makeText(MainActivity.this,"clic sur OK",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog alert= adb.create();
                alert.show();
                break;
            case R.id.b6:
                adb = new AlertDialog.Builder(this);
                adb.setMessage("Message");
                adb.setPositiveButton("OUI",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int
                                    arg1) {
                                Toast.makeText(MainActivity.this,"clic sur OK",Toast.LENGTH_LONG).show();
                            }
                        });
                adb.setNegativeButton("NON",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int
                                    arg1) {
                                finish();
                            }
                        });
                alert= adb.create();
                alert.show();

                break;
            case R.id.b7:
                adb = new AlertDialog.Builder(this);
                adb.setMessage("Message");
                adb.setPositiveButton("OUI",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int
                                    arg1) {
                                Toast.makeText(MainActivity.this,"clic sur OK",Toast.LENGTH_LONG).show();
                            }
                        });
                adb.setNegativeButton("NON",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int
                                    arg1) {
                                finish();
                            }
                        });
                adb.setNeutralButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int
                                    arg1) {
                                finish();
                            }
                        });
                alert= adb.create();
                alert.show();
                break;
        }
    }
}