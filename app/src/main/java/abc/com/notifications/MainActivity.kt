package abc.com.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nm=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // only to work fro device greater than OREO
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
        {
            nm.createNotificationChannel(NotificationChannel("First" ,
                    "default", NotificationManager.IMPORTANCE_DEFAULT))
        }
        button.setOnClickListener {
            val simpleNotification = NotificationCompat.Builder(this,"First")
                .setContentTitle("Simple Title")
                .setContentText("This is sample description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(1,simpleNotification)
        }

        button2.setOnClickListener {
            val i =Intent()
            i.action=Intent.ACTION_VIEW
            i.data= Uri.parse("https://www.google.com")
            val pi =PendingIntent.getActivity(this,123,i,PendingIntent.FLAG_UPDATE_CURRENT)

            val clickableNotification= NotificationCompat.Builder(this, "First")
                .setContentTitle("Simple Title")
                .setContentIntent(pi)
                .setAutoCancel(false)
                .setContentText("This is sample description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(2,clickableNotification)
        }

        button3.setOnClickListener {
            val i =Intent()
            i.action=Intent.ACTION_VIEW
            i.data= Uri.parse("https://www.google.com")
            val pi =PendingIntent.getActivity(this,123,i,PendingIntent.FLAG_UPDATE_CURRENT)

            val clickableNotification= NotificationCompat.Builder(this, "First")
                .setContentTitle("Simple Title")
                .addAction(R.drawable.ic_launcher_foreground, "Click me", pi)
                .setAutoCancel(true)
                .setContentText("This is sample description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_background)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(2,clickableNotification)
        }

    }
}