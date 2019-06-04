package weather.izam.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currently {
    private String summary;
    private double temperature;
    private long time;

    public String getSummary() {
        return summary;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "{" +
                "summary:'" + summary + '\'' +
                ",temperature:" + temperature +
                ",time:" + time +
                '}';
    }
}

