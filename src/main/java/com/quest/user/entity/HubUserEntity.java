package com.quest.user.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data	
@Entity
@Table (name = "2024_hub_user")
public class HubUserEntity {
	
	@Id
	@Column (name = "hub_uid")
	private Long hubUid;	// 하이브 유저 관리하는 id (PK)
	
	@Column (name = "team_id")
	private Long teamId;
	
	@Column (name = "hub_id" , insertable = false , updatable = false)
	private String hubId;	// 2024_team 테이블에서 hub_name 으로 사용
	
	@Column (name = "transit_date")
	private LocalDate transitDate;
	
	@Column (name = "crt_date")
	private LocalDate crtDate;	// 하이브 계정의 생성 날짜
	
	@Column (name = "upd_date")
	private LocalDate updDate;	// 하이브 계정 수정 날짜
	
	@OneToMany(mappedBy = "hubUser", fetch = FetchType.EAGER)
	private List<TeamUserEntity> teams;

}