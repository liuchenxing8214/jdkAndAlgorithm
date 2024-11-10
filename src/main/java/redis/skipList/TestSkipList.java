package redis.skipList;

public class TestSkipList {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        int numElements = 1000000;

        // 插入随机数
        for (int i = 0; i < numElements; i++) {
            Integer randomNum = (int) (Math.random() * numElements);
            skipList.add(randomNum);
            assert skipList.search(randomNum) : "Failed to add " + randomNum + ".";
        }

        // 查找随机数并验证
        for (int i = 0; i < numElements; i++) {
            int randomNum = (int) (Math.random() * numElements);

            assert skipList.search(randomNum) : "Failed to find " + randomNum + " after insertion.";
        }

        // 删除随机数并验证
        for (int i = 0; i < numElements; i++) {
            int randomNum = (int) (Math.random() * numElements);

            skipList.erase(randomNum);
            assert !skipList.search(randomNum) : "Failed to delete " + randomNum + ".";
        }

        System.out.println("All tests passed successfully.");
    }
}
