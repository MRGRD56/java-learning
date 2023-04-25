package com.mrgrd56.models.people.comparator;

import com.mrgrd56.models.people.Gender;
import com.mrgrd56.models.people.Person;

import java.util.Comparator;

public class OlderPersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return Comparator.comparing((Person person) -> {
                        Gender gender = person.getGender();
                        int genderMultiplier = switch (gender) {
                            case MALE -> 1;
                            case FEMALE -> 2;
                        };

                        return person.getAge() * genderMultiplier;
                    })
                    .reversed()
                    .compare(o1, o2);
        }
    }