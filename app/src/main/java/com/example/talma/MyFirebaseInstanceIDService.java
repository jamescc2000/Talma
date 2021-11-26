package com.example.talma;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(@NonNull RemoteMessage mensaje){
        super.onMessageReceived(mensaje);
        //aqui se recibirán todos las notificaciones
    }



}
