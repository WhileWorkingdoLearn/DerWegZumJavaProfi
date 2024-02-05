package org.der.weg.zum.java.profi.FunctionalInterfacesForCollections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {
    public static void main(String[] args) {
        final List<Person> persons = new ArrayList<>();

        persons.add(new Person("Blbl", LocalDate.of(1988,3,6),"London"));
        persons.add(new Person("Bfwfwf", LocalDate.of(1958,9,27),"Munich"));
        persons.add(new Person("Blbl", LocalDate.of(1995,2,16),"Stuttgart"));
        persons.add(new Person("ffwffw", LocalDate.of(2005,6,8),"New York"));
        persons.add(new Person("dddwddwd", LocalDate.of(1988,10,16),"Tokio"));

        final Predicate<Person> isAdult = person -> person.getAge() >= 18;
        final Predicate<Person> inMunich = person -> person.getCity() == "Munich";
        final Predicate<Person> inNewYork = person -> person.getCity() == "New York";
        final Predicate<Person> isYoung = isAdult.negate();
        final Predicate<Person> isYoungAndFromMunich = isYoung.and(inMunich);
        final Predicate<Person> isYoungAndFromMunichor = isYoungAndFromMunich.or(inNewYork);

       List<Person> adultPersons = persons.stream().filter(isAdult).toList();
        persons.removeIf(isYoung);
    }
}
