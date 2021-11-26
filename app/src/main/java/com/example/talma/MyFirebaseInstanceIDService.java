package com.example.talma;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {

    private static final String id_canal_notificacion = "MY_NOTIFICATION_CHANNEL_ID";

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage mensaje){
        super.onMessageReceived(mensaje);
        //aqui se recibirán todos las notificaciones

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        String TipoNotificacion = mensaje.getData().get("tipo");
        if (TipoNotificacion.equals("Reclamo")){
            String EmpleadoID = mensaje.getData().get("empleadoUid");
            String ClienteID = mensaje.getData().get("clienteUid");
            String Titulo = mensaje.getData().get("n_titulo");
            String ReclamoID = mensaje.getData().get("reclamo_id");

            if (firebaseUser != null && firebaseAuth.getUid().equals(EmpleadoID)){

                mostrarNotificacion(EmpleadoID,ClienteID,Titulo,ReclamoID);
            }

        }
        if(TipoNotificacion.equals("Actualizacion")){

        }

    }

    private void mostrarNotificacion(String EmpleadoID, String ClienteID, String Titulo, String ReclamoID){
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        int notificacion = new Random().nextInt(3000);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            
        }
    }



}
