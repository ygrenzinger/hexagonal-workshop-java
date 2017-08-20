package com.kata.hexagonal.domain.dvd;

import org.junit.Before;
import org.junit.Test;

import java.time.Year;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonDvdMatcherTest {

    public CollectionBuilder builder = mock(CollectionBuilder.class);
    public AgeToYearConverter converter = mock(AgeToYearConverter.class);
    public Collection collection;
    public PersonDvdMatcher personDvdMatcher;

    @Before
    public void before() {
        Dvd alice = new Dvd("Alice in wonderlands", Year.of(1992));
        Dvd avatar = new Dvd("Avatar", Year.of(2009));
        when(builder.build()).thenReturn(new Collection(Arrays.asList(alice, avatar)));
        collection = Collection.from(builder);
        personDvdMatcher = new PersonDvdMatcher(collection, converter);
    }


    @Test
    public void should_find_a_match_between_person_and_a_dvd_only_with_date() {
        Person person = new Person("Alice", 25);
        when(converter.convert(25)).thenReturn(Year.of(1992));
        assertThat(personDvdMatcher.dateMatch(person)).contains(new Dvd("Alice in wonderlands", Year.of(1992)));
    }
}
