package dm.bl.creditservice.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendStatusUpdate(String topic, Object message) {
        kafkaTemplate.send(topic, message);
    }
}