package com.kata.hexagonal.domain.dvd;

import java.time.Year;
import java.util.Objects;

public class Dvd {

    private final String title;
    private final Year releaseDate;

    public Dvd(String title, Year releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public String title() {
        return title;
    }

    public Year releaseDate() {
        return releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvd dvd = (Dvd) o;
        return Objects.equals(title, dvd.title) &&
                Objects.equals(releaseDate, dvd.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate);
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
