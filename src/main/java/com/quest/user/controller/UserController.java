package com.quest.user.controller;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quest.user.dto.response.UserResponseDTO;
import com.quest.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "패키지 결제 테스트 계정 확인", description = "입력한 hub_id에 연동되어 있는 team 정보를 확인하는 API")
    public ResponseEntity<List<UserResponseDTO>> getTeamInfoByHubIds(
            @Parameter(description = "hub_id 1", required = true) @RequestParam(required = false) String hubId1,
            @Parameter(description = "hub_id 2", required = false) @RequestParam(required = false) String hubId2,
            @Parameter(description = "hub_id 3", required = false) @RequestParam(required = false) String hubId3,
            @Parameter(description = "hub_id 4", required = false) @RequestParam(required = false) String hubId4,
            @Parameter(description = "hub_id 5", required = false) @RequestParam(required = false) String hubId5) {

        // 입력값 필터링 및 리스트로 변환
        List<String> hubIds = Stream.of(hubId1, hubId2, hubId3, hubId4, hubId5)
                                    .filter(Objects::nonNull)
                                    .filter(hubId -> !hubId.isBlank())
                                    .collect(Collectors.toList());

        if (hubIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<UserResponseDTO> results = userService.getTeamInfoByHubId(hubIds);
        return ResponseEntity.ok(results);
    }
}
