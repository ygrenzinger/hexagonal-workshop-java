package com.kata.hexagonal.domain.dvd;

import org.junit.Test;

import java.time.Year;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionTest {
    @Test
    public void should_find_dvd_in_collection() {
        String title = "Armageddon";
        Dvd dvd = new Dvd(title, Year.of(1998));

        Collection collection = new Collection(Collections.singletonList(dvd));
        Optional<Dvd> foundDVD = collection.findByTitle(title);
        assertThat(foundDVD).contains(new Dvd(title, Year.of(1998)));
    }

    @Test
    public void should_retrieve_all_dvds() {
        String title = "Armageddon";
        Dvd dvd = new Dvd(title, Year.of(1998));

        Collection collection = new Collection(Collections.singletonList(dvd));
        assertThat(collection.all()).contains(dvd);

    }

}