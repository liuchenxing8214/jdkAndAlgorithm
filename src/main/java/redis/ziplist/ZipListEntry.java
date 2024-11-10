package redis.ziplist;

public class ZipListEntry {
    int prevLen;
    byte encoding;
    Object content;

    public ZipListEntry(int prevLen, byte encoding, Object content) {
        this.prevLen = prevLen;
        this.encoding = encoding;
        this.content = content;
    }

    public int getPrevLen() {
        return prevLen;
    }

    public byte getEncoding() {
        return encoding;
    }

    public Object getContent() {
        return content;
    }
}

