package algorithm.package19.test;

import lombok.Data;

@Data
public class Book {
    private String title;
    private int year;

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }
    public Book(){

    }
}