package jdkSeven;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

public class UnSafeTest {
    public static void main(String[] args) throws Exception {
        Integer[] arr = {2,5,1,8,10};
        Unsafe unsafe =  getUnsafe();
        int baseOffset = unsafe.arrayBaseOffset(Integer[].class);
        int indexScale = unsafe.arrayIndexScale(Integer[].class);
        //获取
        Object o = unsafe.getObjectVolatile(arr,(2*indexScale)+baseOffset);
        System.out.println(o);

        //设置
        unsafe.putOrderedObject(arr,(2*indexScale)+baseOffset,100);
        System.out.println(Arrays.toString(arr));


        System.out.println("***********CAS 测试开始  *********");

        //cas,拿着2号角标的值,判断是否为1，如果是设置为101,并且返回true。
        boolean b = unsafe.compareAndSwapInt(arr,(2*indexScale)+baseOffset,1,101);
        System.out.println(b);
        System.out.println(Arrays.toString(arr));




        boolean c = unsafe.compareAndSwapObject(arr,(2*indexScale)+baseOffset,100,101);
        System.out.println(c);
        System.out.println(Arrays.toString(arr));


    }

    /**
     *
     * @return 通过反射获取Unsafe对象
     * @throws Exception
     */
    public static Unsafe getUnsafe() throws Exception{
        Field theUnsafe =  Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        return (Unsafe)theUnsafe.get(null);
    }
}
