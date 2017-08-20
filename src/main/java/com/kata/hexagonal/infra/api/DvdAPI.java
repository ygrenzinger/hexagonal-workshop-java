package com.kata.hexagonal.infra.api;

import com.kata.hexagonal.domain.dvd.Collection;
import com.kata.hexagonal.domain.dvd.Person;
import com.kata.hexagonal.domain.dvd.PersonDvdMatcher;
import com.kata.hexagonal.infra.dto.DvdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DvdAPI {

    private final Collection collection;
    private final PersonDvdMatcher personDvdMatcher;

    @Autowired
    public DvdAPI(Collection collection, PersonDvdMatcher personDvdMatcher) {
        this.collection = collection;
        this.personDvdMatcher = personDvdMatcher;
    }

    @RequestMapping("/")
    @ResponseBody
    public List<DvdDto> all() {
        return collection.all().stream().map(DvdDto::from).collect(Collectors.toList());
    }

    @RequestMapping("/find/by-title/{title}")
    public ResponseEntity<DvdDto> findByTitle(@PathVariable("title") String title) {
        return collection.findByTitle(title)
                .map(dvd -> ResponseEntity.ok(DvdDto.from(dvd)))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping("/match/person/{name}/{age}")
    @ResponseBody
    public List<DvdDto> matchByPerson(@PathVariable("name") String name, @PathVariable("age") int age) {
        Person person = new Person(name, age);
        return personDvdMatcher.dateMatch(person).stream()
                .map(DvdDto::from)
                .collect(Collectors.toList());
    }

}
