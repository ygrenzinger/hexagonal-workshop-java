package com.kata.hexagonal.domain.dvd;

import java.util.List;

public interface CollectionBuilder {

    List<Dvd> retrieveListOfDvds();

    default Collection build() {
        return new Collection(retrieveListOfDvds());
    }

}
