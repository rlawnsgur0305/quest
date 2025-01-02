package com.quest.reward.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DateRangeDTO {
	
	private LocalDate startDate;		// 검색 시작 날짜
	
	private LocalDate endDate;		// 검색 종료 날짜
	
}
