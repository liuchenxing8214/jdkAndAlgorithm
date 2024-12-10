package kafka.pacakge02;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;

import java.util.Collections;
import java.util.Properties;

public class CreateKafkaTopic {
    public static void main(String[] args) {
        // Kafka 配置
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.182.201:9092"); // Kafka broker 地址
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 创建 AdminClient
        try (AdminClient adminClient = AdminClient.create(properties)) {
            // 创建主题
            NewTopic newTopic = new NewTopic("my-topic02", 4, (short) 1); // 4 个分区，复制因子为 1
            adminClient.createTopics(Collections.singleton(newTopic));
            System.out.println("主题 'my-topic' 创建成功，包含 4 个分区。");
        } catch (TopicExistsException e) {
            System.out.println("主题 'my-topic' 已存在。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
