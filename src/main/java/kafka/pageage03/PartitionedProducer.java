package kafka.pageage03;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
public class PartitionedProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.182.201:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group03");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        try {
            for (int i = 0; i < 10; i++) {
                int partition = i % 3;
                ProducerRecord<String, String> record = new ProducerRecord<>("my-topic04", partition, Integer.toString(i), "Message-" + i);
                producer.send(record);
                System.out.println("Sent message to partition " + partition + ": " + "Message-" + i);
            }
        } finally {
            producer.close();
        }
    }
}

