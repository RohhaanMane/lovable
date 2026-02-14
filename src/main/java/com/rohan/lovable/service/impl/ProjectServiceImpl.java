package com.rohan.lovable.service.impl;

import com.rohan.lovable.dto.project.ProjectRequest;
import com.rohan.lovable.dto.project.ProjectResponse;
import com.rohan.lovable.dto.project.ProjectSummaryResponse;
import com.rohan.lovable.entity.Project;
import com.rohan.lovable.entity.User;
import com.rohan.lovable.repository.ProjectRepository;
import com.rohan.lovable.repository.UserRepository;
import com.rohan.lovable.service.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectServiceImpl implements ProjectService {

    UserRepository userRepository;
    ProjectRepository projectRepository;

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
//        User owner = userRepository.getReferenceById(userId);
        return List.of();
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        return null;
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();

        project = projectRepository.save(project);
        return projectMapp
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        return null;
    }

    @Override
    public void softDelete(Long id, Long userId) {

    }
}
