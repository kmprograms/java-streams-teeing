package kmprograms;

import kmprograms.model.Hobby;
import kmprograms.model.Person;
import kmprograms.service.PersonService;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        var people = List.of(

                Person.builder()
                        .name("JON")
                        .age(10)
                        .hobbies(EnumSet.of(Hobby.PROGRAMMING, Hobby.SWIMMING))
                        .build(),

                Person
                        .builder()
                        .name("TYRION")
                        .age(20)
                        .hobbies(EnumSet.of(Hobby.TRAVELLING, Hobby.SWIMMING))
                        .build(),

                Person
                        .builder()
                        .name("BRAN")
                        .age(30)
                        .hobbies(EnumSet.of(Hobby.PROGRAMMING, Hobby.TRAVELLING))
                        .build(),

                Person
                        .builder()
                        .name("CERSEI")
                        .age(40)
                        .hobbies(EnumSet.of(Hobby.PROGRAMMING, Hobby.SWIMMING))
                        .build());

        // ---------------------------------------- 1 ----------------------------------------------

        double averageAge = people
                .stream()
                .collect(Collectors.teeing(
                        Collectors.summingDouble(Person::getAge),
                        Collectors.counting(),
                        (ageSum, counter) -> ageSum / counter
                ));
        System.out.println("Average age = " + averageAge);

        // ---------------------------------------- 2 ----------------------------------------------

        people
                .stream()
                .collect(
                        Collectors.teeing(
                                Collectors.filtering(PersonService::doesLikeProgramming, Collectors.toList()),
                                Collectors.filtering(PersonService::isAdult, Collectors.toList()),
                                PersonService::mergePeople
                        )).forEach(System.out::println);

        // ---------------------------------------- 3 ----------------------------------------------

        System.out.println(people
                .stream()
                .collect(Collectors.teeing(
                        Collectors.mapping(Person::getName, Collectors.joining(", ")),
                        Collectors.filtering(PersonService::isAdult, Collectors.mapping(Person::getName, Collectors.joining(", "))),
                        ( String allNames, String adultNames ) -> allNames + "\n" + adultNames
                )));


    }


}
