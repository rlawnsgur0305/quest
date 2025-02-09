package com.quest.reward.util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import com.opencsv.CSVWriter;
import com.quest.reward.dto.response.RewardResponseDTO;

public class RewardCSVUtil {

	public static void writeRewardDataCsv(OutputStreamWriter writer, List<RewardResponseDTO> data) throws IOException {
	    CSVWriter csvWriter = new CSVWriter(writer);

	    // Header (컬럼 생성)
	    String[] header = { "team_id", "total_amount", "status" , "reward" };
	    csvWriter.writeNext(header);

	    // 데이터 작성
	    if (data == null || data.isEmpty()) {
	        String[] row = { "결제 데이터가 없습니다, 다른 날짜로 다시 시도해주세요","",""};
	        csvWriter.writeNext(row);
	    } else {
	        for (RewardResponseDTO reward : data) {
	            String[] row = { String.valueOf(reward.getTeamId()), String.valueOf(reward.getTotalAmount()), reward.getUserStatus(),
	                    reward.getReward() };
	            csvWriter.writeNext(row);
	        }
	    }

	    csvWriter.close();
	}

}
