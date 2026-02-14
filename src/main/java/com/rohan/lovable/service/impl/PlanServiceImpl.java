package com.rohan.lovable.service.impl;

import com.rohan.lovable.dto.project.FileContentResponse;
import com.rohan.lovable.dto.project.FileNode;
import com.rohan.lovable.dto.subscription.PlanResponse;
import com.rohan.lovable.service.FileService;
import com.rohan.lovable.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {


    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
