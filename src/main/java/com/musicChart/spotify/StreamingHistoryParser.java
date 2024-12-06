package com.musicChart.spotify;

import org.json.JSONArray;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StreamingHistoryParser {
    public static List<StreamingRecord> parseStreamingHistory(String filePath) throws IOException {
        List<StreamingRecord> records = new ArrayList<>();
        FileReader fileReader = new FileReader(filePath);
        StringBuilder sb = new StringBuilder();
        int i;
        while ((i = fileReader.read()) != -1) {
            sb.append((char) i);
        }
        fileReader.close();

        JSONArray historyArray = new JSONArray(sb.toString());
        for (int j = 0; j < historyArray.length(); j++) {
            var record = historyArray.getJSONObject(j);
            String artistName = record.getString("artistName");
            String trackName = record.getString("trackName");
            long msPlayed = record.getLong("msPlayed");
            records.add(new StreamingRecord(artistName, trackName, msPlayed));
        }
        return records;
    }
}
