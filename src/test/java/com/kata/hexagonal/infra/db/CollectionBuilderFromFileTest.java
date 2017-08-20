package com.kata.hexagonal.infra.db;

import com.kata.hexagonal.domain.dvd.Collection;
import com.kata.hexagonal.domain.dvd.CollectionBuilder;
import com.kata.hexagonal.domain.dvd.Dvd;
import org.junit.Test;

import java.time.Year;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionBuilderFromFileTest {

    @Test
    public void should_find_dvd_from_file_collection() {

        CollectionBuilder builder = new CollectionBuilderFromFile("/file/dvds.txt");
        Collection collection = Collection.from(builder);

        Optional<Dvd> foundDVD = collection.findByTitle("Armageddon");
        assertThat(foundDVD).contains(new Dvd("Armageddon", Year.of(1998)));
    }

}