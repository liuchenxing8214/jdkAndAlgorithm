package algorithm.package19.test;

import java.util.Comparator;

public class TitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        // Compare books based on their titles
        return b1.getYear() - b2.getYear();
    }
}