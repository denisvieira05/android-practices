package org.js.denisvieira.exampleeventbus.models;

/**
 * Created by denisvieira on 08/08/16.
 */

public class Person {
    private String name;
    private String job;

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
}
