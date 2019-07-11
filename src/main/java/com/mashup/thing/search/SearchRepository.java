package com.mashup.thing.search;

import com.mashup.thing.search.domain.Search;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SearchRepository extends JpaRepository<Search, Long> {
    Optional<List<Search>> findByUserId(Long id);
}
