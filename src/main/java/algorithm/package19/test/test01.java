package algorithm.package19.test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class test01 {
    public static void main(String[] args) {
        List<Book> library = new ArrayList<>();
        library.add(new Book("Java Programming", 2019));
        library.add(new Book("Algorithms Unleashed", 2022));
        library.add(new Book("Data Structures Explained", 2017));

/*        // Sort the list of books based on the title using the TitleComparator
        Collections.sort(library, new TitleComparator());

        for (Book book : library) {
            System.out.println(book.getTitle() + " (" + book.getYear() + ")");
        }*/
        PriorityQueue<Book> heap = new PriorityQueue<>(new TitleComparator());
        for (Book book : library) {
            heap.add(book);
        }
        while (!heap.isEmpty()){
            System.out.println(heap.poll());
        }
    }
}
