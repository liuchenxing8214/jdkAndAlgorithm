package InterviewJVM;

public class Main {
    public static void main(String[] args) {
        try {
            // 尝试加载Child类
            Class.forName("InterviewJVM.Child");
            // 再次尝试加载Parent类
            Class.forName("InterviewJVM.Parent");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}