package com.rohan.lovable.service.impl;

import com.rohan.lovable.dto.project.ProjectRequest;
import com.rohan.lovable.dto.project.ProjectResponse;
import com.rohan.lovable.dto.project.ProjectSummaryResponse;
import com.rohan.lovable.entity.Project;
import com.rohan.lovable.entity.ProjectMember;
import com.rohan.lovable.entity.ProjectMemberId;
import com.rohan.lovable.entity.User;
import com.rohan.lovable.enums.ProjectRole;
import com.rohan.lovable.error.ResourceNotFoundException;
import com.rohan.lovable.mapper.ProjectMapper;
import com.rohan.lovable.repository.ProjectMemberRepository;
import com.rohan.lovable.repository.ProjectRepository;
import com.rohan.lovable.repository.UserRepository;
import com.rohan.lovable.service.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectServiceImpl implements ProjectService {

    UserRepository userRepository;
    ProjectRepository projectRepository;
    ProjectMemberRepository projectMemberRepository;
    ProjectMapper projectMapper;

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        List<Project> projectList = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toProjectSummaryResponseList(projectList);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = getAccessibleProjectsById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();

        project = projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .role(ProjectRole.OWNER)
                .user(owner)
                .project(project)
                .invitedAt(Instant.now())
                .acceptedAt(Instant.now())
                .build();

        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project = getAccessibleProjectsById(id, userId);
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectsById(id, userId);
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    private Project getAccessibleProjectsById(Long projectId, Long userId) {
        return projectRepository.findAllAccessibleById(projectId, userId)
                .orElseThrow(()->new ResourceNotFoundException("Project", projectId.toString()));
    }
}
