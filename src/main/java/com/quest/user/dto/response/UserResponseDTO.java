package com.quest.user.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
	
	private String hubName; // 하이브 아이디
	
	private Long teamId; // 하이브 아이디에 연결되어 있는 팀 번호
	
	private String status; // 팀 상태
	
	private LocalDate crtDate; // 팀 생성 날짜
	
}
