package com.kata.hexagonal.domain.dvd;

import java.time.Year;

public interface AgeToYearConverter {

    Year convert(int age);

}
