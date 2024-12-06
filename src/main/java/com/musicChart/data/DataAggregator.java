package com.musicChart.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DataAggregator {
    public static Map<String, Double> getTopArtists(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> records = mapper.readValue(Paths.get(filePath).toFile(), List.class);

        Map<String, Integer> artistPlaytime = records.stream()
                .collect(Collectors.groupingBy(
                        record -> (String) record.get("artistName"),
                        Collectors.summingInt(record -> (int) record.get("msPlayed"))
                ));

        // Convert ms to minutes and get the top 5 artists
        return artistPlaytime.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() / 60000.0,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
    public static void main(String[] args) throws Exception {
        Map<String, Double> topArtists = DataAggregator.getTopArtists("src/main/resources/StreamingHistory_music_0.json");
        topArtists.forEach((artist, minutes) -> System.out.println(artist + ": " + minutes + " minutes"));
    }

}

