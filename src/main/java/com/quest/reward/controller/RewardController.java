package com.quest.reward.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quest.reward.dto.response.RewardResponseDTO;
import com.quest.reward.service.RewardServiceImpl;
import com.quest.reward.util.CSVUtil;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class RewardController {

	private final RewardServiceImpl rewardServiceImpl;

	@GetMapping("/reward")
	@Operation(summary = "캐시백 이벤트 리워드 조회", description = "캐시백 이벤트 보상과 총 결제금액 조회시 사용하는 API")
	public List<RewardResponseDTO> getReward(
			@RequestParam(value = "startDate", defaultValue = "2024-01-01") LocalDate startDate,
			@RequestParam(value = "endDate", defaultValue = "2024-12-31") LocalDate endDate) {

		List<RewardResponseDTO> result = rewardServiceImpl.getReward(startDate, endDate);

		return result;
	}

	@GetMapping("/reward/download")
	@Operation(summary = "캐시백 이벤트 리워드 결과 다운로드", description = "캐시백 이벤트 보상과 총 결제금액 조회결과를 다운로드 할 때 사용하는 API")
	public void downloadReward(@RequestParam(value = "startDate", defaultValue = "2024-01-01") LocalDate startDate,
			@RequestParam(value = "endDate", defaultValue = "2024-12-31") LocalDate endDate,
			HttpServletResponse response) throws IOException {

		List<RewardResponseDTO> result = rewardServiceImpl.getReward(startDate, endDate);

		// MS949 인코딩 및 Content-Type 설정
		response.setContentType("application/octet-stream; charset=MS949");
		response.setHeader("Content-Disposition", "attachment; filename=\"cashback_reward_data.csv\"");

		try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "MS949")) {
			CSVUtil.writeRewardDataCsv(writer, result);
		}
	}
}