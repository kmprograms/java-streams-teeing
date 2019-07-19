package kmprograms.model;

import lombok.Builder;
import lombok.Value;

import java.util.EnumSet;

@Value
@Builder
public class Person {
    String name;
    int age;
    EnumSet<Hobby> hobbies;
}
