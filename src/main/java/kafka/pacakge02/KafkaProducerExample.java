package kafka.pacakge02;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducerExample {
    public static void main(String[] args) {
        // 配置Kafka生产者属性
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.182.201:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // 创建Kafka生产者
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        String topic = "my-topic02";
        for (int i = 0; i < 10; i++) {
            String key = "key" + (i % 3); // 确保相同的键，保证消息进入相同的分区
            String value = "message-" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
            try {
                // 发送消息并打印元数据
                RecordMetadata metadata = producer.send(record).get();
                System.out.printf("Sent record with key %s and value %s to partition %d with offset %d%n",
                        key, value, metadata.partition(), metadata.offset());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 关闭生产者
        producer.close();
    }
}

