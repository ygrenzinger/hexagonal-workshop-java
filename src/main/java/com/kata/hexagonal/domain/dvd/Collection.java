package com.kata.hexagonal.domain.dvd;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Collection {

    private final List<Dvd> dvds;

    Collection(List<Dvd> dvds) {
        this.dvds = dvds;
    }

    public Optional<Dvd> findByTitle(String title) {
        return dvds.stream().filter(dvd -> dvd.title().equalsIgnoreCase(title)).findFirst();
    }

    public List<Dvd> all() {
        return Collections.unmodifiableList(dvds);
    }

    public static Collection from(CollectionBuilder builder) {
        return builder.build();
    }
}
