package com.omargroup.movies_thyme.repository;

import com.omargroup.movies_thyme.model.ImageMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMetadataRepository extends JpaRepository<ImageMetaData, Long> {

}

