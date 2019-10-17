package com.cherry.prospring5.ch12.repos;

import com.cherry.prospring5.ch12.entities.Singer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SingerRepository extends CrudRepository<Singer, Long> {
    List<Singer> findByFirstName(String firstName);
}
