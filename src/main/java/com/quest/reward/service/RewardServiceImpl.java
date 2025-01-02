package com.quest.reward.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.quest.reward.dto.response.RewardResponseDTO;
import com.quest.reward.entity.CashbackRewardEntity;
import com.quest.reward.entity.PurchaseLogEntity;
import com.quest.reward.repository.CashbackRewardRepository;
import com.quest.reward.repository.PurchaseLogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

	private final PurchaseLogRepository purchaseLogRepository;
	private final CashbackRewardRepository cashbackRewardRepository;

	/**
	 * 검색 시작 날짜(startDate)와 종료 날짜(endDate)를 기준으로 유저별 결제 내역을 합산하고, 보상을 반환합니다!!
	 */
	@Override
	public List<RewardResponseDTO> getReward(LocalDate startDate, LocalDate endDate) {

		// 1. 주어진 날짜 범위에 해당하는 모든 구매 기록을 가져옴.
		List<PurchaseLogEntity> purchaseLogs = purchaseLogRepository.findByCrtDateBetween(startDate, endDate);

		// 2. 유저별로 결제 금액을 합산 계산
		Map<Long, Long> teamTotalAmounts = new HashMap<>(); // 유저 ID별 총 결제 금액을 저장할 Map

		for (PurchaseLogEntity purchaseLog : purchaseLogs) {
			Long teamId = purchaseLog.getTeamId();
			Long price = purchaseLog.getPrice();

			// 이미 해당 팀의 결제 금액이 있으면 합산
			if (teamTotalAmounts.containsKey(teamId)) {
				teamTotalAmounts.put(teamId, teamTotalAmounts.get(teamId) + price);
			} else {
				teamTotalAmounts.put(teamId, price);
			}
		}
		
		// 3. 캐시백 보상 기준 데이터를 가져오기.
		List<CashbackRewardEntity> rewardList = cashbackRewardRepository.findAll();
		
		// 4. 각 유저의 총 결제 금액에 맞는 보상 계산하기
		List<RewardResponseDTO> rewardResponses = new ArrayList<>();
		
		
		for (Map.Entry<Long , Long> entry : teamTotalAmounts.entrySet()) {
			Long teamId = entry.getKey(); // 보상 대상인 teamId;
			Long totalAmount = entry.getValue(); // 유저의 총 결제 금액
			
			// 여기는 총 결제 금액에 맞는 수령 예정 보상
			String reward = null;
			
			// 결과 생성
			RewardResponseDTO responseDTO = new RewardResponseDTO();
			responseDTO.setTeamId(teamId);
			responseDTO.setTotalAmount(totalAmount);
			
			// 결제 금액별 보상 결정
			
			// 5만
			if (totalAmount < 50000) {
				continue;
			}
			// 5만원 이상 10만원 미만
			else if (totalAmount >= 50000 && totalAmount < 100000) {
				responseDTO.setReward(rewardList.get(0).getReward());
				
				// 10만원 이상 20만원 미만
			} else if (totalAmount >= 100000 && totalAmount < 200000) {
				responseDTO.setReward(rewardList.get(1).getReward());
				
				// 20만원 이상 30만원 미만
			} else if (totalAmount >= 200000 && totalAmount < 300000) {
				responseDTO.setReward(rewardList.get(2).getReward());
				
				// 30만원 이상 50만원 미만
			} else if (totalAmount >= 300000 && totalAmount < 500000) {
				responseDTO.setReward(rewardList.get(3).getReward());
				
				// 50만원 이상 100만원 미만
			} else if (totalAmount >= 500000 && totalAmount < 1000000) {
				responseDTO.setReward(rewardList.get(4).getReward());
				
				// 100만원 이상
			} else responseDTO.setReward(rewardList.get(5).getReward());
			
			rewardResponses.add(responseDTO);

		}
		
		// 5. 결과 반환
		return rewardResponses;
	}

}
