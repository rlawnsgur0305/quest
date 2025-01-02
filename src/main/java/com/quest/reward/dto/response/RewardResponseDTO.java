package com.quest.reward.dto.response;

import lombok.Data;

@Data
public class RewardResponseDTO {

	private Long teamId;		// 보상 수령 대상 팀
	
	private Long totalAmount;		// 팀의 총 결제 금액

	private String reward;		// 보상

}
