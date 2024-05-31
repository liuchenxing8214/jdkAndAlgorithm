package jdkSeven;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Person {
    static final Unsafe UNSAFE;

    //要更新的字段
    private volatile long state;

    //记录字段的偏移量
    private static final long stateOffset;

    /**
     * 静态块初始化unsafe,并且获取state字段的偏移量
     */
    static {
        try {
            //反射获取unsafe
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            UNSAFE = (Unsafe) f.get(null);
            //获取偏移量
            stateOffset = UNSAFE.objectFieldOffset(Person.class.getDeclaredField("state"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public Person(long state) {
        this.state = state;
    }

    public static void main(String[] args) {
        Person person = new Person(0);
        //尝试更改变量值
        boolean b = UNSAFE.compareAndSwapLong(person, stateOffset, person.state, 2);
        System.out.println(b);
        System.out.println(person.state);
    }
}
