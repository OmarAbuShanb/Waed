package waed.dev.ps.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import waed.dev.ps.R;

public class UtilsNotifications {
    private static final String TAG = "UtilsNotifications";

    public static void setUpNotification(
            Context context,
            int notificationId,
            String title,
            String details
    ) {
        String NOT_CHANNEL_NAME = "notification messaging";
        long[] NOT_VIBRATION_PATTERN = {0, 120, 70, 150, 70, 600};
        Bitmap defaultUserImage
                = getBitmapFromVectorDrawable(context, R.mipmap.ic_launcher_round);

        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(context, String.valueOf(notificationId))
                .setContentTitle(title)
                .setContentText(details)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(defaultUserImage)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(details))
                .setVibrate(NOT_VIBRATION_PATTERN)
                .setPriority(Notification.PRIORITY_MAX)
                .setCategory(Notification.CATEGORY_ALARM)
                .setAutoCancel(true);


        NotificationManagerCompat notManager = NotificationManagerCompat.from(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notManager.getNotificationChannel(NOT_CHANNEL_NAME) == null) {
                NotificationChannel notChannel = new NotificationChannel(
                        String.valueOf(notificationId),
                        NOT_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_HIGH);

                notChannel.setDescription("NOT_CHANNEL_Description");
                notChannel.setVibrationPattern(NOT_VIBRATION_PATTERN);
                notChannel.enableVibration(true);
                notManager.createNotificationChannel(notChannel);
            }
        }

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
            notManager.notify(notificationId, notBuilder.build());
        }
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        assert drawable != null;
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888
        );
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
