package javaBase.comp;

public class Test01 {
    public static void main(String[] args) {
        String a ="aaaa";
        String b = new String("aaaa");
        Integer c=100;
        Integer d =300;
        Integer e = 100;
        Integer f = 300;
        System.out.println(c==e);
        System.out.println(a=="aaaa");
        System.out.println(b=="aaaa");
        System.out.println(d==f);

        StringBuilder builder = new StringBuilder();
        StringBuffer buffer = new StringBuffer();
        buffer.append("A");
    }
}
