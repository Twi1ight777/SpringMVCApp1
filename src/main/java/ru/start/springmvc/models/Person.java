package ru.start.springmvc.models;

public class Person {
    private int id;
    private String name;

    public Person() {

    }

    public void Person (){
        this.id = 0;
        this.name = null;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
