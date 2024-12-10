package kafka.pacakge02;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
    private static final String TOPIC = "my-topic02";
    private static final String GROUP_ID = "my-group02";

    public static void main(String[] args) {
        // 创建两个消费者实例
        Thread consumerA = new Thread(() -> startConsumer("Consumer A"));
        Thread consumerB = new Thread(() -> startConsumer("Consumer B"));
        Thread consumerC = new Thread(() -> startConsumer("Consumer C"));

        consumerA.start();
        consumerB.start();
        consumerC.start();
    }

    private static void startConsumer(String consumerName) {
        // 配置消费者属性
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.182.201:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

        // 创建 Kafka 消费者
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Collections.singletonList(TOPIC)); // 订阅主题

            while (true) {
                // 拉取消息
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("%s consumed message from partition %d: %s%n", consumerName, record.partition(), record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



