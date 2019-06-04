package weather.izam.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private double latitude;
    private double longitude;
    private String timezone;
    private Currently currently;

    public String getTimezone() {
        return this.timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Currently getCurrently() {
        return currently;
    }
    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    @Override
    public String toString() {
        return "{" +
            "timezone:'" + this.getTimezone() + "'," +
            "latitude:" + this.getLatitude() + ',' +
            "longitude:" + this.getLongitude()  +
            ", currently:" + currently +
            '}';
    }
}
