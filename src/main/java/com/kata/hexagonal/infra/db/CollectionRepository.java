package com.kata.hexagonal.infra.db;

import com.kata.hexagonal.infra.dto.DvdDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository  extends CrudRepository<DvdDto, Long> {
}
