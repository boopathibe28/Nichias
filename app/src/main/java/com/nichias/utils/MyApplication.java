package com.nichias.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;


import androidx.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nichias.utils._pref.SessionManager;
import com.nichias.utils._pref.SharedPrefConstants;
import com.onesignal.OSNotificationAction;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Fresco.initialize(this);
        /*OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(OSNotificationOpenResult result) {
                        JSONObject data = result.toJSONObject();
                        String type = "";
                        String order_key = "";
                        try {
                            type = String.valueOf(data.getJSONObject("notification").getJSONObject("payload").getJSONObject("additionalData").get("notification_type"));
                            order_key = String.valueOf(data.getJSONObject("notification").getJSONObject("payload").getJSONObject("additionalData").get("order_key"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (type.equals("2")) {
                            // CommonFunctions.splash = "true";
                          //  Intent intent = new Intent(getApplicationContext(), OrderReceiptActivity.class);
                          //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                         //   intent.putExtra("order_key", order_key);
                         //   startActivity(intent);
                        }
                    }
                }).init();
*/
        SessionManager.getInstance().insertIntoPreference(context, SharedPrefConstants.DEVICE_TYPE, "1");

       /* Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("DB.realm")
                .schemaVersion(2) // Must be bumped when the schema changes
                .migration(new MyMigration()) // Migration to run
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
*/
    }
/*

    class NotificationReceivedHandler implements OneSignal.NotificationOpenedHandler {

        @Override
        public void notificationOpened(OSNotificationOpenResult result) {

            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;
            if (data != null) {
                    */
/*try {
                        String bookingKey = data.getString("booking_key");
                        String notificationType = data.getString("notification_type");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*//*

            }
            System.out.println("Notification Data -----"+data);
            String customKey;
        }
    }
*/


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

}
