package com.quest.user.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
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
import com.quest.user.util.UserCSVUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "패키지 결제 테스트 계정 확인", description = "입력한 hub_id에 연동되어 있는 team 정보를 확인하는 API")
    public ResponseEntity<List<UserResponseDTO>> getTeamInfoByHubIds(
            @Parameter(description = "hub_id 리스트", required = true, example = "cu2qa151,cu2qa152")
            @RequestParam List<String> hubIds) {

        // 입력값 필터링
        List<String> filteredHubIds = hubIds.stream()
                                            .filter(Objects::nonNull)
                                            .filter(hubId -> !hubId.isBlank())
                                            .collect(Collectors.toList());

        if (filteredHubIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<UserResponseDTO> results = userService.getTeamInfoByHubId(filteredHubIds);
        return ResponseEntity.ok(results);
    }
    
    @GetMapping("/download")
    @Operation(summary = "패키지 결제 테스트 계정 다운로드", description = "입력한 hub_id에 연동되어 있는 team 정보를 확인하고 다운받는 API")
    public void downloadTeamInfo(
            @Parameter(description = "hub_id 1", required = true) @RequestParam(required = false) String hubId1,
            @Parameter(description = "hub_id 2", required = false) @RequestParam(required = false) String hubId2,
            @Parameter(description = "hub_id 3", required = false) @RequestParam(required = false) String hubId3,
            @Parameter(description = "hub_id 4", required = false) @RequestParam(required = false) String hubId4,
            @Parameter(description = "hub_id 5", required = false) @RequestParam(required = false) String hubId5,
            HttpServletResponse response) throws IOException {

        // 입력값 필터링 및 리스트로 변환
        List<String> hubIds = Stream.of(hubId1, hubId2, hubId3, hubId4, hubId5)
                                    .filter(Objects::nonNull)
                                    .filter(hubId -> !hubId.isBlank())
                                    .collect(Collectors.toList());

        List<UserResponseDTO> results = userService.getTeamInfoByHubId(hubIds);

        // 대한민국 현재 시간 가져오기
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        LocalDateTime now = LocalDateTime.now(seoulZoneId);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        String formattedDate = now.format(dateFormatter.withZone(seoulZoneId));

        // MS949 인코딩 및 Content-Type 설정
        response.setContentType("application/octet-stream; charset=MS949");
        response.setHeader("Content-Disposition", "attachment; filename=\"team_data.csv\"");
        response.setHeader("Date", formattedDate); // 대한민국 시간으로 Date 헤더 설정

        try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "MS949")) {
            UserCSVUtil.writeTeamDataCsv(writer, results);
        }
    }
}
