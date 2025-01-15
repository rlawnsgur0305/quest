package com.quest.user.util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.quest.user.dto.response.UserResponseDTO;

public class UserCSVUtil {

    public static void writeTeamDataCsv(OutputStreamWriter writer, List<UserResponseDTO> data) throws IOException {
        // 최대 너비를 설정 (정렬된 테이블 모양을 위해)
        final int COL_WIDTH = 20; // 각 컬럼의 고정 너비 (원하는 너비로 조정 가능)
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // 헤더 생성
        String header = formatRow(new String[] { "hub_name", "team_id", "status", "crt_date" }, COL_WIDTH);
        writer.write("+---------------------+---------------------+---------------------+---------------------+\n");
        writer.write(header);
        writer.write("+---------------------+---------------------+---------------------+---------------------+\n");

        // 데이터 작성
        if (data == null || data.isEmpty()) {
            String emptyRow = formatRow(new String[] { "No Data", "", "", "" }, COL_WIDTH);
            writer.write(emptyRow);
        } else {
            for (UserResponseDTO user : data) {
                String[] row = {
                        user.getHubName(),
                        String.valueOf(user.getTeamId()),
                        user.getStatus(),
                        user.getCrtDate().format(dateFormatter)
                };
                writer.write(formatRow(row, COL_WIDTH));
            }
        }
        writer.write("+---------------------+---------------------+---------------------+---------------------+\n");
        writer.flush(); // 반드시 데이터를 작성 후 flush
    }

    private static String formatRow(String[] columns, int colWidth) {
        StringBuilder rowBuilder = new StringBuilder("|");
        for (String col : columns) {
            if (col == null) col = ""; // null 값을 빈 문자열로 처리
            rowBuilder.append(String.format(" %-"+colWidth+"s|", col)); // 고정 너비로 정렬
        }
        rowBuilder.append("\n");
        return rowBuilder.toString();
    }
}
