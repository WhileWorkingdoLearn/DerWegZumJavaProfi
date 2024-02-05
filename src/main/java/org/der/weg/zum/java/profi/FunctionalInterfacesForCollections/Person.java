package org.der.weg.zum.java.profi.FunctionalInterfacesForCollections;

import java.time.LocalDate;
import java.util.Objects;

public class Person implements Comparable<Person> {
    private final String name;
    private final LocalDate date;
    private final int age;
    private final String City;


    public Person(String name, LocalDate date, String city) {
        this.name = name;
        this.date = date;
        this.age = 10;
        City = city;
    }

    public int getAge() {
        return this.age;
    }


    public LocalDate getDate() {
        return date;
    }

    public String getCity() {
        return City;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return compareTo(person) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, City);
    }

    @Override
    public int compareTo(Person person) {
       return this.name.compareTo(person.name);
    }

}
