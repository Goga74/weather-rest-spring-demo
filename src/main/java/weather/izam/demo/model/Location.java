package weather.izam.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location extends LocationPOJO {
    public Location() {
    }

    public Location(final double lat, final double lon) {
        this.setLatitude(lat);
        this.setLongitude(lon);
    }

    @Override
    public String toString() {
        return "Location{" +
            "ip='" + this.getIp() + "'," +
            "country='" + this.getCountry() + "'," +
            "latitude=" + this.getLatitude() + ',' +
            "longitude=" + this.getLongitude() +
            '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Location.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Location other = (Location) obj;
        if ((this.getCountry() == null) ? (other.getCountry() != null) : !this.getCountry().equals(other.getCountry())) {
            return false;
        }

        if (Math.abs(this.getLongitude() - other.getLongitude()) > 0.000001) {
            return false;
        }

        if (Math.abs(this.getLatitude() - other.getLatitude()) > 0.000001) {
            return false;
        }

        return true;
    }
}
