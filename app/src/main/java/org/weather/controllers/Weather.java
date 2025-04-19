package org.weather.controllers;
import java.util.logging.Logger;
import io.github.cdimascio.dotenv.Dotenv;
import java.nio.file.Paths;

public class Weather {
    private static final Logger logger = Logger.getLogger(Weather.class.getName());
    private final String apiUrl;
    private final String apiKey;

    public Weather() {
        String envPath = Paths.get(System.getProperty("user.dir"), "..", ".env").normalize().toString();
        if (!Paths.get(envPath).toFile().exists()) {
            logger.warning("The .env file does not exist at the specified path: " + envPath);
        }
        Dotenv dotenv = Dotenv.configure()
                .directory(envPath)
                .ignoreIfMissing()
                .filename(".env")
                .load();

        this.apiUrl = dotenv.get("WEATHER_API_URL", "https://api.openweathermap.org/data/2.5/weather");
        this.apiKey = dotenv.get("OPENWEATHER_APP_ID");

        if (this.apiKey == null || this.apiKey.isEmpty()) {
            logger.severe("OPENWEATHER_APP_ID is not set in the .env file");
            throw new IllegalStateException("OPENWEATHER_APP_ID is not set in the .env file");
        }

        logger.info("Weather API initialized with URL: " + this.apiUrl);
    }

    public String getApiUrl() {
        return apiUrl + "?appid=" + apiKey;
    }
}
 