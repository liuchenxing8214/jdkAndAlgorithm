package redis.dict;

public class DictionaryDemo {
    public static void main(String[] args) {
        Dictionary<String, Integer> dict = new Dictionary<>(4); // 初始容量为4

        dict.put("Alice", 1);
        dict.put("Bob", 2);

        System.out.println("Alice: " + dict.get("Alice")); // 输出: Alice: 1
        System.out.println("Bob: " + dict.get("Bob"));     // 输出: Bob: 2

        dict.put("Charlie", 3);
        dict.put("David", 4);

        System.out.println("Charlie: " + dict.get("Charlie")); // 输出: Charlie: 3

        dict.put("Eve", 5); // 此时会触发扩容

        System.out.println("Eve: " + dict.get("Eve"));         // 输出: Eve: 5

        System.out.println("Bob after adding Eve: " + dict.get("Bob")); // 确保 Bob 仍然存在，输出: Bob: 2

        dict.shrink(); // 尝试收缩字典

        System.out.println("After shrinking:");

        System.out.println("Alice: " + dict.get("Alice"));
        System.out.println("Bob: " + dict.get("Bob"));

    }
}
