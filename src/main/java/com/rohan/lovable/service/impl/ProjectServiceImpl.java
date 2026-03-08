package com.rohan.lovable.service.impl;

import com.rohan.lovable.dto.project.ProjectRequest;
import com.rohan.lovable.dto.project.ProjectResponse;
import com.rohan.lovable.dto.project.ProjectSummaryResponse;
import com.rohan.lovable.entity.Project;
import com.rohan.lovable.entity.ProjectMember;
import com.rohan.lovable.entity.ProjectMemberId;
import com.rohan.lovable.entity.User;
import com.rohan.lovable.enums.ProjectRole;
import com.rohan.lovable.error.BadRequestException;
import com.rohan.lovable.error.ResourceNotFoundException;
import com.rohan.lovable.mapper.ProjectMapper;
import com.rohan.lovable.repository.ProjectMemberRepository;
import com.rohan.lovable.repository.ProjectRepository;
import com.rohan.lovable.repository.UserRepository;
import com.rohan.lovable.security.AuthUtil;
import com.rohan.lovable.service.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
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
    AuthUtil authUtil;

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authUtil.getCurrentUserId();
        List<Project> projectList = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toProjectSummaryResponseList(projectList);
    }

    @Override
    @PreAuthorize("@security.canViewProject(#projectId)")
    public ProjectSummaryResponse getUserProjectById(Long projectId) {
        Long userId = authUtil.getCurrentUserId();

        var projectWithRole = projectRepository.findAccessibleProjectByIdWithRole(projectId, userId)
                .orElseThrow(() -> new BadRequestException("Project Not Found"));

        return projectMapper.toProjectSummaryResponse(projectWithRole.getProject(), projectWithRole.getRole());
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
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
    @PreAuthorize("@security.canEditProject(#projectId)")
    public ProjectResponse updateProject(Long projectId, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectsById(projectId, userId);
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#projectId)")
    public void softDelete(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectsById(projectId, userId);
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    private Project getAccessibleProjectsById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(()->new ResourceNotFoundException("Project", projectId.toString()));
    }
}
