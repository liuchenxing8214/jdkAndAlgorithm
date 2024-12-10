package kafka.sort;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaMessageProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.182.201:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            // 发送插入消息
            producer.send(new ProducerRecord<>("my-topic02", "1", "insert"));
            // 发送更新消息
            producer.send(new ProducerRecord<>("my-topic02", "1", "update"));
            System.out.println("Messages sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
