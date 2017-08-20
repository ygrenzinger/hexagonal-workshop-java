package com.kata.hexagonal.infra.db;

import com.kata.hexagonal.domain.dvd.Collection;
import com.kata.hexagonal.domain.dvd.Dvd;
import com.kata.hexagonal.infra.dto.DvdDto;
import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CollectionBuilderFromDBTest {


    @Autowired
    private CollectionRepository collectionRepository;

    private Collection collection;

    @Before
    public void before() {
        collectionRepository.save(new DvdDto("Armageddon", "1998"));
        CollectionBuilderFromDB builder = new CollectionBuilderFromDB(collectionRepository);
        collection = Collection.from(builder);
    }

    @Test
    public void repository_should_have_armageddon() {
        assertThat(collectionRepository.findAll())
                .extracting("title", "releaseDate")
                .containsExactly(Tuple.tuple("Armageddon", "1998"));
    }

    @Test
    public void should_build_collection_from_db() {
        assertThat(collection.all())
                .extracting("title", "releaseDate")
                .containsExactly(Tuple.tuple("Armageddon", Year.of(1998)));
    }

    @Test
    public void should_find_armageddon() {
        assertThat(collection.findByTitle("Armageddon")).isPresent();
        assertThat(collection.findByTitle("Armageddon").get())
                .isEqualToComparingOnlyGivenFields(new Dvd("Armageddon", Year.of(1998)), "title", "releaseDate");
    }
}