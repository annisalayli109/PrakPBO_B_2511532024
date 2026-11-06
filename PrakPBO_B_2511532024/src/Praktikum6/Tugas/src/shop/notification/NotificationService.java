package shop.notification;

import java.util.ArrayList;

public class NotificationService {
    private final ArrayList<NotificationChannel> channels = new ArrayList<>();

    public void register(NotificationChannel channel) {
        if (channel != null) {
            channels.add(channel);
        }
    }

    public int broadcast(CustomerContact contact, String message) {
        int successCount = 0;

        for (NotificationChannel channel : channels) {
            if (channel.send(contact, message)) {
                successCount++;
            }
        }

        return successCount;
    }
}
