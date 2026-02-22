package com.rohan.lovable.repository;

import com.rohan.lovable.entity.ProjectMember;
import com.rohan.lovable.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

}
