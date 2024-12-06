package com.musicChart.spotify;

import java.io.IOException;
import java.util.*;

public class MusicChartApp {
    public static void main(String[] args) {
        try {
            // Parse the streaming history data
            List<StreamingRecord> records = StreamingHistoryParser.parseStreamingHistory("src/main/resources/StreamingHistory_music_0.json");

            // Aggregate data (group by artist and track name, and sum the msPlayed)
            Map<String, Double> aggregatedData = MusicChartAggregator.aggregateData(records);

            // Get the top 5 entries
            List<Map.Entry<String, Double>> topEntries = MusicChartAggregator.getTopEntries(aggregatedData, 5);

            // Fetch access token from Spotify
            String token = SpotifyAPI.getAccessToken();

            // Print the top 5 artist-track combinations with details from Spotify
            for (Map.Entry<String, Double> entry : topEntries) {
                String artistTrack = entry.getKey();
                double minutesPlayed = entry.getValue();
                System.out.println("Artist - Track: " + artistTrack + " | Minutes Played: " + minutesPlayed);

                String[] artistTrackSplit = artistTrack.split(" - ");
                String artist = artistTrackSplit[0];
                String track = artistTrackSplit[1];

                // Get track info from Spotify
                String response = SpotifyAPI.searchTrack(token, artist, track);
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
