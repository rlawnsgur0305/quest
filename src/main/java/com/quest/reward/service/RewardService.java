package com.quest.reward.service;
import java.time.LocalDate;
import java.util.List;

import com.quest.reward.dto.response.RewardResponseDTO;

public interface RewardService {
	
	public List<RewardResponseDTO> getReward(LocalDate startDate , LocalDate endDate);
	
}
