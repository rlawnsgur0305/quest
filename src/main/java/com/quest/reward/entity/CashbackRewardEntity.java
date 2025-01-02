package com.quest.reward.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity // 엔티티에요
@Table (name = "cash_back_reward") // 테이블이에요
public class CashbackRewardEntity {
	
	
	@Id
	@Column(name= "price")
	private Long price; // 보상 수령 기준 가격이에요
	
	@Column(name= "reward")
	private String reward; // 보상 내용이에요
	
	

}
