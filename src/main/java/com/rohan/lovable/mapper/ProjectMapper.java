package com.rohan.lovable.mapper;


import com.rohan.lovable.dto.project.ProjectResponse;
import com.rohan.lovable.dto.project.ProjectSummaryResponse;
import com.rohan.lovable.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);
    ProjectSummaryResponse toProjectSummaryResponse(Project project);
    List<ProjectSummaryResponse> toProjectSummaryResponseList(List<Project> projects);
}
