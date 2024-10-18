package InterviewJVM;

class Example {
    public static void main(String[] args) {
        Object a = new Object(); // 创建对象a
        Object b = a;            // b强引用对象a

        a = null;                // a现在为null，但b仍然引用对象

        // 此时，虽然a为null，b仍然引用着同一个对象
        System.out.println("对象是否可回收？: " + (b != null)); // 输出: 对象是否可回收？: true
    }
}
