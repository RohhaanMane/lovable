package com.rohan.lovable.service;

import com.rohan.lovable.dto.project.FileContentResponse;
import com.rohan.lovable.dto.project.FileNode;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}
