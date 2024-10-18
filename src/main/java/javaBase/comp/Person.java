package javaBase.comp;

import java.util.Objects;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // 引用相同
        if (obj == null || getClass() != obj.getClass()) return false; // 类型检查
        Person person = (Person) obj; // 类型转换
        return age == person.age && name.equals(person.name); // 内容比较
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age); // 也应该重写hashCode
    }


    public static void main(String[] args) {
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Alice", 30);
        System.out.println(p1.equals(p2)); // 输出 true，因为内容相同
        System.out.println(p1==p2);
    }

}
