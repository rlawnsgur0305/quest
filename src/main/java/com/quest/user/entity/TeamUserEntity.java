package com.quest.user.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "2024_team")
public class TeamUserEntity {
	
	@Id
	@Column (name = "team_id")
	private Long teamId;
	
	@Column (name = "team_name")
	private String teamName;
	
	@Column (name = "hub_yn")
	private char hubYn; // 팀의 하이브 계정 연동 여부
	
	@Column (name = "hub_name" , insertable = false , updatable = false)
	private String hubName; // 2024_hub_user 테이블의 hub_id 와 매칭되는 컬럼
	
	@Column (name = "status")
	private String status; // 팀의 현재 상태 DEL == 삭제된 팀 , OK == 생성되어 있는 팀
	
	@Column (name = "crt_date")
	private LocalDate crtDate;
	
	@Column (name = "upd_date")
	private LocalDate updDate;
	
	@ManyToOne
	@JoinColumn(name = "hub_name" , referencedColumnName = "hub_id")
	private HubUserEntity hubUser;

}
