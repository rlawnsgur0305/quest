package com.quest.reward.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity // 엔티티에요
@Table (name = "9up_purchase_log") // 테이블이에요
public class PurchaseLogEntity {
	
	@Id
	@Column(name= "purchase_log_id")
	private Long purchaseLogId; // 구매 기록 아이디에요
	
	@Column(name="team_id")
	private Long teamId; // 팀 아이디에요
	
	@Column(name= "market_code")
	private String marketCode; // 결제 진행한 플랫폼이에요
	
	@Column(name="product_id")
	private String productId; // 결제한 상품이에요
	
	@Column(name= "price")
	private Long price; // 결제 금액이에요
	
	@Column(name= "crt_date")
	private LocalDate crtDate; // 구매 기록의 생성 날짜 시간이에요
	
}
