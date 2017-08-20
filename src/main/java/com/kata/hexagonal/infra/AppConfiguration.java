package com.kata.hexagonal.infra;

import com.kata.hexagonal.domain.dvd.AgeToYearConverter;
import com.kata.hexagonal.domain.dvd.Collection;
import com.kata.hexagonal.domain.dvd.CollectionBuilder;
import com.kata.hexagonal.domain.dvd.PersonDvdMatcher;
import com.kata.hexagonal.infra.db.CollectionBuilderFromDB;
import com.kata.hexagonal.infra.db.CollectionBuilderFromFile;
import com.kata.hexagonal.infra.db.CollectionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Year;

@Configuration
public class AppConfiguration {

    private AgeToYearConverter ageToYearConverter() {
        return age -> Year.now().minusYears((long)age);
    }

    @Bean
    public PersonDvdMatcher personDvdMatcher(Collection collection) {
        return new PersonDvdMatcher(collection, ageToYearConverter());
    }

    @Bean
    public CollectionBuilder collectionBuilderFromDB(CollectionRepository collectionRepository) {
        return new CollectionBuilderFromDB(collectionRepository);
    }

    @Bean
    @Primary
    public CollectionBuilder collectionBuilderFromFile() {
        return new CollectionBuilderFromFile("/file/dvds.txt");
    }

    @Bean
    public Collection collection(CollectionBuilder collectionBuilder) {
        return Collection.from(collectionBuilder);
    }

}
