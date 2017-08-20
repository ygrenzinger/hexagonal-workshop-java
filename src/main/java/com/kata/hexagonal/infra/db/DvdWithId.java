package com.kata.hexagonal.infra.db;

import com.kata.hexagonal.domain.dvd.Dvd;

import java.time.Year;

public class DvdWithId extends Dvd {
    private final Long id;

    public DvdWithId(Long id, String title, Year year) {
        super(title, year);
        this.id = id;
    }

    public Long id() {
        return id;
    }

    @Override
    public String toString() {
        return "DvdWithId{" +
                "id=" + id +
                '}';
    }
}
