package com.rohan.lovable.service.impl;

import com.rohan.lovable.dto.project.FileContentResponse;
import com.rohan.lovable.dto.project.FileNode;
import com.rohan.lovable.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, String path, Long userId) {
        return null;
    }
}
