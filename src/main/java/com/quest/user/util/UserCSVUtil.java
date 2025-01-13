package com.quest.user.util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import com.opencsv.CSVWriter;
import com.quest.user.dto.response.UserResponseDTO;

public class UserCSVUtil {

	public static void writeTeamDataCsv(OutputStreamWriter writer, List<UserResponseDTO> data) throws IOException {
	    CSVWriter csvWriter = new CSVWriter(writer);

	    // Header (컬럼 생성)
	    String[] header = { "hub_name", "team_id" , "status", "upd_date" };
	    csvWriter.writeNext(header);

	    // 데이터 작성
	    if (data == null || data.isEmpty()) {
	        String[] row = { "요청하신 hub계정에 연결된 team이 존재하지 않습니다.","","",""};
	        csvWriter.writeNext(row);
	    } else {
	        for (UserResponseDTO user : data) {
	            String[] row = { String.valueOf(user.getHubName()), String.valueOf(user.getTeamId()),
	                    user.getStatus() , String.valueOf(user.getUpdDate()) };
	            csvWriter.writeNext(row);
	        }
	    }

	    csvWriter.close();
	}

}
