package com.quest.reward.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity // 엔티티에요
@Table (name = "9up_team") // 테이블이에요
public class TeamEntity {
	
	
	@Id
	@Column(name= "team_id")
	private Long teamId; // 팀 아이디에요
	
	@Column(name="team_name")
	private String teamName; // 팀 이름이에요
	
	@Column(name= "hub_name")
	private String hubName; // 연동된 허브 계정 이름이에요
	
	@Column(name="status")
	private String status; // 팀 삭제 / 유지 상태에요
	
	@Column(name= "crt_date")
	private LocalDate crtDate; // 계정의 생성 날짜 시간이에요
	
	@Column(name= "upd_date")
	private LocalDate updDate; // 계정의 수정 시간이에요
	
	@OneToOne(mappedBy = "team")
	private UserEntity user;
}
