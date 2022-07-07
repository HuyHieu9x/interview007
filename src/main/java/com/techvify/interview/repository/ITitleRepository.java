package com.techvify.interview.repository;

import com.techvify.interview.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITitleRepository extends JpaRepository<Title, Integer>, CrudRepository<Title,Integer> {

    @Query(value = "SELECT * FROM title where deleted_at = :isDeleted limit :start,:size",nativeQuery = true)
    List<Title> findAllPaginate(@Param("start") int start ,@Param("size") int size,@Param("isDeleted") boolean isDeleted);

    Title findByName(String name);

    boolean existsByName(String name);

    boolean existsById(int id);
}
