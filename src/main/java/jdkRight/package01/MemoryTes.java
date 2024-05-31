package jdkRight.package01;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class MemoryTes {
    public static void main(String[] args) throws Exception {
        Vo vo= new Vo();
        Vo vo2= new Vo();
        System.out.println(vo.hashCode());
        System.out.println(vo2.hashCode());
        Unsafe unsafe = geyUnsafeInstance();
        long aoffset = unsafe.objectFieldOffset(Vo.class.getDeclaredField("a"));
        long boffset = unsafe.objectFieldOffset(Vo.class.getDeclaredField("b"));
        System.out.println(aoffset);
        System.out.println(boffset);
//            System.out.println(unsafe.getInt(vo,11));
//            System.out.println(unsafe.getInt(vo2,16));
        System.out.println(unsafe.compareAndSwapInt(vo,boffset,0,1));
        System.out.println(vo.b);

    }

    public static Unsafe geyUnsafeInstance() throws Exception{
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }
}
