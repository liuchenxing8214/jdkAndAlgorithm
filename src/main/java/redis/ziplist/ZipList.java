package redis.ziplist;

import java.util.ArrayList;
import java.util.List;

public class ZipList {
    private List<ZipListEntry> entries;

    public ZipList() {
        entries = new ArrayList<>();
    }

    // 添加条目
    public void addEntry(byte encoding, Object content) {
        int prevLen = entries.isEmpty() ? 0 : getEntrySize(entries.get(entries.size() - 1));
        ZipListEntry entry = new ZipListEntry(prevLen, encoding, content);
        entries.add(entry);
    }

    // 获取条目的大小
    private int getEntrySize(ZipListEntry entry) {
        return Integer.BYTES + Byte.BYTES + getContentSize(entry.content);
    }

    // 获取内容的大小
    private int getContentSize(Object content) {
        System.out.println("进入getContentSize"+content);
        if (content instanceof String) {
            return ((String) content).length();
        } else if (content instanceof Integer) {
            return getIntegerSize((Integer) content);
        } else if(content instanceof Long){
            return Long.SIZE;
        }
        return 0;
    }

    // 根据整数类型获取大小
    private int getIntegerSize(Integer value) {
        if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE) {
            return Byte.BYTES;
        } else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE) {
            return Short.BYTES;
        } else if (value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE) {
            return Integer.BYTES;
        } else {
            return Long.BYTES;
        }
    }

    // 获取条目
    public ZipListEntry getEntry(int index) {
        return entries.get(index);
    }

    // 查找条目
    public ZipListEntry findEntry(Object content) {
        for (ZipListEntry entry : entries) {
            if (entry.getContent().equals(content)) {
                return entry;
            }
        }
        return null;
    }

    // 删除条目
    public void removeEntry(Object content) {
        ZipListEntry entryToRemove = findEntry(content);
        if (entryToRemove != null) {
            entries.remove(entryToRemove);
        }
    }

    // 获取列表大小
    public int size() {
        return entries.size();
    }
}
