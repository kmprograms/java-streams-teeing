package kmprograms.service;

import kmprograms.model.Hobby;
import kmprograms.model.Person;

import java.util.List;

public interface PersonService {

    static List<Person> mergePeople( List<Person> people1, List<Person> people2  ) {
        people1.retainAll(people2);
        return people1;
    }

    static boolean isAdult( Person person ) {
        return person != null && person.getAge() >= 18;
    }

    static boolean doesLikeProgramming( Person person ) {
        return person != null && person.getHobbies().contains(Hobby.PROGRAMMING);
    }
}
