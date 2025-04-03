package com.omargroup.movies_thyme.repository;

import com.omargroup.movies_thyme.model.MovieCast;
import com.omargroup.movies_thyme.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>, JpaSpecificationExecutor<Person> {





}
