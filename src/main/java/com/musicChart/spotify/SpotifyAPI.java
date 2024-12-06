package com.musicChart.spotify;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

public class SpotifyAPI {
    private static final String CLIENT_ID = "8f55bccb10534ca78532988d9eccf8ac";  // Replace with your actual Client ID
    private static final String CLIENT_SECRET = "1de4f2a534b34454864ae467ce38c465";  // Replace with your actual Client Secret
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";

    /**
     * Fetches the access token from Spotify using the Client Credentials Flow
     */
    public static String getAccessToken() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        // Prepare the request body
        RequestBody body = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .build();

        // Create the request
        Request request = new Request.Builder()
                .url(TOKEN_URL)
                .header("Authorization", "Basic " + encodedCredentials)
                .post(body)
                .build();

        // Execute the request and get the response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Parse the JSON response to extract the access token
            JSONObject jsonResponse = new JSONObject(response.body().string());
            return jsonResponse.getString("access_token");
        }
    }

    /**
     * Search for a track in Spotify by artist and track name
     */
    public static String searchTrack(String token, String artist, String track) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = HttpUrl.parse("https://api.spotify.com/v1/search").newBuilder()
                .addQueryParameter("q", artist + " " + track)
                .addQueryParameter("type", "track")
                .build();

        // Create the request with the authorization header
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + token)
                .build();

        // Execute the request and get the response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Return the response body as a string
            return response.body().string();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        try {
            // Fetch access token
            String token = getAccessToken();

            // Search for a track (replace with actual artist and track name)
            String artist = "Mark";
            String track = "Makkayala";
            String response = searchTrack(token, artist, track);

            // Print the response (could be processed further as JSON)
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
