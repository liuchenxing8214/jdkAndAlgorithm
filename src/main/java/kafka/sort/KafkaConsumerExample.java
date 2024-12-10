package kafka.sort;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KafkaConsumerExample {
    private final Map<String, Lock> locks = new ConcurrentHashMap<>();
    private final Map<String, String> updateCache = new ConcurrentHashMap<>();

    public void startConsumer() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.182.201:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group02");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Collections.singletonList("my-topic02"));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    processMessage(record);
                }
            }
        }
    }

    private void processMessage(ConsumerRecord<String, String> record) {
        String id = record.key();
        String action = record.value();

        Lock lock = locks.computeIfAbsent(id, k -> new ReentrantLock());

        lock.lock();
        try {
            if ("insert".equals(action)) {
                System.out.println("Processing insert for ID: " + id);
                // 模拟插入逻辑
                Thread.sleep(1000);

                // 检查是否有未处理的更新
                if (updateCache.containsKey(id)) {
                    System.out.println("Found pending update for ID: " + id);
                    // 处理更新逻辑
                    doUpdate(id);
                    updateCache.remove(id); // 清除缓存
                }
            } else if ("update".equals(action)) {
                System.out.println("Processing update for ID: " + id);

                // 如果没有对应的插入，则缓存更新
                if (!dataExists(id)) { // 假设 dataExists 方法检查数据库中是否存在该 ID 的数据
                    System.out.println("Caching update for ID: " + id);
                    updateCache.put(id, action);
                } else {
                    doUpdate(id);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    private boolean dataExists(String id) {
        // 模拟检查数据库中是否存在该 ID 的数据
        return false; // 假设数据不存在
    }

    private void doUpdate(String id) throws InterruptedException {
        System.out.println("Updating data for ID: " + id);
        Thread.sleep(1000); // 模拟更新操作耗时
    }

    public static void main(String[] args) {
        KafkaConsumerExample consumerExample = new KafkaConsumerExample();
        consumerExample.startConsumer();
    }
}
