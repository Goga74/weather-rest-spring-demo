package weather.izam.demo.persistence;

import weather.izam.demo.controller.IPConstraint;
import weather.izam.demo.model.Currently;
import weather.izam.demo.model.Location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    @IPConstraint
    private String ip;
    private String country;
    private String timezone;
    private long time;

    private double latitude;
    private double longitude;

    @NotNull
    private String summary;
    private double temperature;

    public void setLocation(Location location) {
        this.setCountry(location.getCountry());
        this.setIp(location.getIp());
        this.setLatitude(location.getLatitude());
        this.setLongitude(location.getLongitude());
    }

    public void setCurrently(Currently currently) {
        this.setTime(currently.getTime());
        this.setSummary(currently.getSummary());
        this.setTemperature(currently.getTemperature());
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
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

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getTemperature() {
        return this.temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

}


