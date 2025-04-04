package com.example.dewanpannel.Class

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager



class Notificationcass:Application(){
    public val Chanel1="Channel1"
    public val Channel2="Channel2"
    override fun onCreate() {
        super.onCreate()
        val channel=NotificationChannel(Chanel1,Chanel1,NotificationManager.IMPORTANCE_HIGH)
        channel.description="hell tpo"

        val manager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)


        val channeltwo= NotificationChannel(Channel2,Channel2,NotificationManager.IMPORTANCE_HIGH)
        channeltwo.description=" Hiii tpo"

        var managertwo=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channeltwo)
    }

}