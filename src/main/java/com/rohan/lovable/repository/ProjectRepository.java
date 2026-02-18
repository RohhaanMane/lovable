package com.rohan.lovable.repository;

import com.rohan.lovable.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("""
            SELECT p FROM Project p
            WHERE p.deletedAt IS NULL
            AND p.owner.id = :userId
            ORDER by p.updatedAt DESC
        """)
    List<Project> findAllAccessibleByUser(@Param("userId") Long userId);

//    @Query("""
//            SELECT p FROM Project p
//
//        """)
    List<Project> findAllAccessibleById(@Param("projectId") Long projectId, @Param("userId")  Long userId);
}
