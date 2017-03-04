package hello.objects;

import java.util.List;

/**
 * Created by aaronfleshner on 3/2/17.
 */
public class Person {

    public String name;
    public String imageUrl;
    public int age;
    public String job;
    public List<Pet> pets;

    public Person() {

    }

    public Person(String name, int age, String job, String url, List<Pet> pets) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.pets = pets;
        this.imageUrl = url;
    }
}
