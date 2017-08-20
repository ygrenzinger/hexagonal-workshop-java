package com.kata.hexagonal.infra.db;

import com.kata.hexagonal.domain.dvd.CollectionBuilder;
import com.kata.hexagonal.domain.dvd.Dvd;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionBuilderFromFile implements CollectionBuilder {

    private final List<Dvd> dvds;

    public CollectionBuilderFromFile(String pathToFile) {
        this.dvds = readFrom(pathToFile);
    }

    private List<Dvd> readFrom(String pathToFile) {
        try {
            Path path = Paths.get(this.getClass().getResource(pathToFile).toURI());
            return Files.readAllLines(path).stream().map(line -> {
                String[] vals = line.split(";");
                return new Dvd(vals[0], Year.of(Integer.parseInt(vals[1])));
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Dvd> retrieveListOfDvds() {
        return new ArrayList<>(dvds);
    }
}
