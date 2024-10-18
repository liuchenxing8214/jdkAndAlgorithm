package InterviewJVM;

public class test01 {
    public static void main(String[] args) {
        System.out.println("A");
        new test01();
        new test01();
    }

    public test01(){
        System.out.println("B");
    }

    {
        System.out.println("C");
    }
    static {
        System.out.println("D");
    }


}
