package classes;

import java.util.Objects;

public class SportsmenInfo {
    private String country;
    private String kindOfSport;
    private String fullName;
    private int place;

    public SportsmenInfo(String country, String kindOfSport, String fullName, int place) {
        this.country = country;
        this.kindOfSport = kindOfSport;
        this.fullName = fullName;
        this.place = place;
    }

    public String getCountry() {
        return country;
    }

    public String getKindOfSport() {
        return kindOfSport;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        SportsmenInfo info = (SportsmenInfo) obj;
        return place == info.place && fullName.equals(info.fullName) && kindOfSport.equals(info.kindOfSport) && country.equals(info.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, kindOfSport, fullName, place);
    }

    @Override
    public String toString() {
        return "Country: " + country + ", KindOfSport: " + kindOfSport + ", FullName: " + fullName + ", Place: " + place;
    }
}
