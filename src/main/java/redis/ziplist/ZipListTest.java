package redis.ziplist;

public class ZipListTest {
    public static void main(String[] args) {
        ZipList zipList = new ZipList();

        // 添加条目
/*        zipList.addEntry((byte) 0x00, "Hello");
        zipList.addEntry((byte) 0x01, 123);
        zipList.addEntry((byte) 0x00, "World");
        zipList.addEntry((byte) 0x01, -128);
        zipList.addEntry((byte) 0x01, 32767);*/
        zipList.addEntry((byte) 0x01, 2147483647);
        zipList.addEntry((byte) 0x01,9223372036854775807L);
        zipList.addEntry((byte) 0x01, 32767);

        // 获取并打印条目
        for (int i = 0; i < zipList.size(); i++) {
            ZipListEntry entry = zipList.getEntry(i);
            System.out.println("Entry " + i + ":");
            System.out.println("  prevLen: " + entry.getPrevLen());
            System.out.println("  encoding: " + entry.getEncoding());
            System.out.println("  content: " + entry.getContent());
        }

        // 查找并打印条目
        ZipListEntry foundEntry = zipList.findEntry("Hello");
        if (foundEntry != null) {
            System.out.println("Found entry with content 'Hello':");
            System.out.println("  prevLen: " + foundEntry.getPrevLen());
            System.out.println("  encoding: " + foundEntry.getEncoding());
            System.out.println("  content: " + foundEntry.getContent());
        }

        // 删除条目
        zipList.removeEntry("Hello");
        System.out.println("Entry with content 'Hello' exists after delete: " + (zipList.findEntry("Hello") != null));
    }
}
