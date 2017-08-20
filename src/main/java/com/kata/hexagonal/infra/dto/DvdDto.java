package com.kata.hexagonal.infra.dto;

import com.kata.hexagonal.domain.dvd.Dvd;
import com.kata.hexagonal.infra.db.DvdWithId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class DvdDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String releaseDate;

    public DvdDto() {
    }

    public DvdDto(String title, String releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public DvdDto(Long id, String title, String releaseDate) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public static DvdDto from(Dvd dvd) {
        if (dvd instanceof DvdWithId) {
            DvdWithId dvdWithId = (DvdWithId)dvd;
            return new DvdDto(dvdWithId.id(), dvd.title(), dvd.releaseDate().toString());
        }
        return new DvdDto(null, dvd.title(), dvd.releaseDate().toString());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DvdDto dvdDto = (DvdDto) o;
        return Objects.equals(id, dvdDto.id) &&
                Objects.equals(title, dvdDto.title) &&
                Objects.equals(releaseDate, dvdDto.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseDate);
    }

    @Override
    public String toString() {
        return "DvdDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
