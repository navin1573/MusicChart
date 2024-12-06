# Music Chart Application

## Project Overview
The Music Chart application is a Java-based project that aggregates listening data and fetches additional information using the [Spotify API](https://developer.spotify.com/). The goal is to display the top artists and tracks based on the total playtime (`msPlayed`) from a user's listening history. The project uses Spotify's API to retrieve track details, album artwork, and artist profiles.

### Key Features:
- Fetch music data from Spotify API.
- Aggregate data based on listening history (e.g., artist name, track name, total playtime).
- Visual representation of top artists and tracks using charts.
- Integration with **Spotify API** for fetching album covers and artist details.
- Visualization of data with **JFreeChart** (optional, for visual graphs).

## Technologies Used:
- **Java**: The main programming language used for the project.
- **Spring Boot**: For setting up the web server and handling HTTP requests.
- **Spotify API**: For fetching music-related data such as track, artist, and album information.
- **OkHttp**: For making HTTP requests to the Spotify API.
- **JFreeChart**: For optional data visualization of the top artists and tracks.
- **Jackson and org.json**: For JSON parsing and handling Spotify API responses.
- **Maven**: For dependency management and building the project.

## Prerequisites:
- Java 17 or higher.
- Maven installed on your system.
- Spotify Developer account and API credentials (Client ID and Client Secret).

## Setup Instructions:

### Step 1: Clone the Repository
```bash
git clone https://github.com/yourusername/music-chart.git
cd music-chart
