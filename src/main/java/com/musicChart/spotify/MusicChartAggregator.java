package com.musicChart.spotify;

import java.util.*;

public class MusicChartAggregator {
    public static Map<String, Double> aggregateData(List<StreamingRecord> records) {
        Map<String, Double> artistTotalPlaytime = new HashMap<>();

        for (StreamingRecord record : records) {
            String artistTrackKey = record.getArtistName() + " - " + record.getTrackName();
            double playtimeInMinutes = record.getMsPlayed() / 60000.0; // Convert to minutes

            artistTotalPlaytime.put(artistTrackKey, artistTotalPlaytime.getOrDefault(artistTrackKey, 0.0) + playtimeInMinutes);
        }
        return artistTotalPlaytime;
    }

    public static List<Map.Entry<String, Double>> getTopEntries(Map<String, Double> aggregatedData, int topN) {
        List<Map.Entry<String, Double>> sortedList = new ArrayList<>(aggregatedData.entrySet());
        sortedList.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));
        return sortedList.subList(0, Math.min(topN, sortedList.size()));
    }
}
