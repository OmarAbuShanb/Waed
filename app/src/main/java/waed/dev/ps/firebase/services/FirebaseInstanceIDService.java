package waed.dev.ps.firebase.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;

import waed.dev.ps.Utils.UtilsNotifications;

public class FirebaseInstanceIDService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseInstanceIDServ";

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        int senderId = Integer.parseInt(Objects.requireNonNull(remoteMessage.getSenderId()).substring(0, 6));
        String title = remoteMessage.getData().get("title");
        String details = remoteMessage.getData().get("details");

        UtilsNotifications.setUpNotification(
                getApplicationContext(), senderId, title, details
        );
    }

    @Override
    public void onDeletedMessages() {
        // when there are too many messages (>100) pending for your app on a particular device at
        // the time it connects or if the device hasn't connected to FCM in more than one month
        Log.d(TAG, "onDeletedMessages: ");
    }
}
