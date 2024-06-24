package algorithm.package20;

public class TwoArray {
    public static void main(String[] args) {
        int rows = 4;
        int columns = 5;

        int[][] array = new int[rows][columns];

        int value = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = value;
                value++;
            }
        }

        System.out.println("The 2D array is: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(array.length);
    }
}
