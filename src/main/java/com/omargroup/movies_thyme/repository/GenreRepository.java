package com.omargroup.movies_thyme.repository;

import com.omargroup.movies_thyme.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
    Genre findByName(String categoryName);

    Page<Genre> findAll(Pageable pageable);


}
