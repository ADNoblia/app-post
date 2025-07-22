package com.noblia.app.repositories;

import java.util.List;
import java.util.Optional;

import com.noblia.app.dtos.PageResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.noblia.app.entities.PageEntity;

@Repository
public interface PageRepository extends JpaRepository<PageEntity, Long> {
    
    // SELECT * FROM page WHERE title = :title
    Optional<PageEntity> findByTitle(String title);
    Boolean existsByTitle(String title);

    // DELETE * FROM page WHERE title = :title
    @Modifying
    @Query("DELETE FROM PageEntity WHERE title = :title")
    void deleteByTitle(String title);



}
