package com.quest.reward.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.opencsv.CSVWriter;
import com.quest.reward.dto.response.RewardResponseDTO;

public class CSVUtil {
	
	public static void writeRewardDataCsv(PrintWriter writer , List<RewardResponseDTO> data) throws IOException {
		CSVWriter csvWriter = new CSVWriter(writer);
		
		// Header (컬럼 생성)
		String[] header = { "team_id" , "total_amount" , "reward"};
		csvWriter.writeNext(header);
		
		if (data == null || data.isEmpty()) {
			csvWriter.close();
			throw new IOException("보상 내역이 없습니다 csv 파일을 생성하지 않습니다.");
		}
		
		
		// 내부 데이터 (row 생성)
		for (RewardResponseDTO reward : data) {
			String[] row = {
					String.valueOf(reward.getTeamId()),
					String.valueOf(reward.getTotalAmount()),
					reward.getReward()
			};
			csvWriter.writeNext(row);
		}
		csvWriter.close();
	}
}
