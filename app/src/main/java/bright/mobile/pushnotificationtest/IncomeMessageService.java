package bright.mobile.pushnotificationtest;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

public class IncomeMessageService extends FirebaseMessagingService {

    private static String TAG = "INCOME_MESSAGE_SERVICE";
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        Log.e(TAG, message.getMessageId());

        RemoteMessage.Notification notification = message.getNotification();
        assert notification != null;
        String title = notification.getTitle();
        String messageBody = notification.getBody();

        Map<String, String> data = message.getData();
        if (!data.isEmpty()){
            title = data.getOrDefault("message_title","");
            messageBody = data.getOrDefault("message_body","");
        }

        NotificationHandler notificationHandler =
                new NotificationHandler(getApplicationContext(), title, messageBody);
        notificationHandler.send();
    }



    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        //TODO: send token to your server
        Log.e(TAG, token);
    }
}
