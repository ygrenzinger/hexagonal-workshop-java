package com.kata.hexagonal.infra.db;

import com.kata.hexagonal.domain.dvd.CollectionBuilder;
import com.kata.hexagonal.domain.dvd.Dvd;
import com.kata.hexagonal.infra.dto.DvdDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CollectionBuilderFromDB implements CollectionBuilder {

    private final CollectionRepository repository;

    @PostConstruct
    public void init() {
        repository.save(new DvdDto("Armageddon", "1998"));
        repository.save(new DvdDto("Avatar", "2009"));
    }

    @Autowired
    public CollectionBuilderFromDB(CollectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Dvd> retrieveListOfDvds() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(dto -> new DvdWithId(dto.getId(), dto.getTitle(), Year.parse(dto.getReleaseDate())))
                .collect(Collectors.toList());

    }
}
