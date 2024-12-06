package com.musicChart.spotify;

public class StreamingRecord {
    private String artistName;
    private String trackName;
    private long msPlayed;

    public StreamingRecord(String artistName, String trackName, long msPlayed) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.msPlayed = msPlayed;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public long getMsPlayed() {
        return msPlayed;
    }
}
