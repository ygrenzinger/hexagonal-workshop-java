package com.kata.hexagonal.domain.dvd;

import java.util.List;
import java.util.stream.Collectors;

public class PersonDvdMatcher {
    private final Collection collection;
    private final AgeToYearConverter ageToYearConverter;

    public PersonDvdMatcher(Collection collection, AgeToYearConverter ageToYearConverter) {
        this.collection = collection;
        this.ageToYearConverter = ageToYearConverter;
    }

    public List<Dvd> dateMatch(Person person) {
        return collection.all().stream()
                .filter(dvd -> dvd.releaseDate().equals(ageToYearConverter.convert(person.age())))
                .collect(Collectors.toList());
    }
}
