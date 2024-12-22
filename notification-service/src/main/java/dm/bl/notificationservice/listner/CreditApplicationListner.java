package dm.bl.notificationservice.listner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CreditApplicationListner {

    @KafkaListener(topics = "credit-application-status", groupId = "notification-group")
    public void listen(ConsumerRecord<String, String> record) {
        String key = record.key();
        String value = record.value();

        sendNotification(key, value);
    }

    private void sendNotification(String applicationId, String newStatus) {
        System.out.printf("Sending notification: Application %s updated to status %s%n", applicationId, newStatus);
    }

}
