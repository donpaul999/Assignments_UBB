package com.example.Lab_9;

public class City {
    private String id;
    private String name;

    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public City() {
        this.id = "";
        this.name = "";
    }
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
