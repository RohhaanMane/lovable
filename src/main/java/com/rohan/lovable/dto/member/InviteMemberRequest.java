package com.rohan.lovable.dto.member;

import com.rohan.lovable.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
