package dm.bl.creditservice.listener;

import dm.bl.creditservice.entity.CreditStatus;
import dm.bl.creditservice.publisher.KafkaPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class StatusEventListener {
    private final KafkaPublisher kafkaPublisher;
    @Value("${topics.credits.status}")
    private String topic;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendToNotifyService(CreditStatus status) {
        kafkaPublisher.sendStatusUpdate(topic, status.name());
    }
}
