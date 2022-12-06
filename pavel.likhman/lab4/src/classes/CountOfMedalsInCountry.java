package classes;

public class CountOfMedalsInCountry {
    private String country;
    private int countFirstPlaces;
    private int countSecondPlaces;
    private int countThirdPlaces;

    public CountOfMedalsInCountry(String country, int firstPlaces, int secondPlaces, int thirdPlaces) {
        this.country = country;
        countFirstPlaces = firstPlaces;
        countSecondPlaces = secondPlaces;
        countThirdPlaces = thirdPlaces;
    }

    public String getCountry() {
        return country;
    }

    public int getCountFirstPlaces() {
        return countFirstPlaces;
    }

    public int getCountSecondPlaces() {
        return countSecondPlaces;
    }

    public int getCountThirdPlaces() {
        return countThirdPlaces;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountFirstPlaces(int countFirstPlaces) {
        this.countFirstPlaces = countFirstPlaces;
    }

    public void setCountSecondPlaces(int countSecondPlaces) {
        this.countSecondPlaces = countSecondPlaces;
    }

    public void setCountThirdPlaces(int countThirdPlaces) {
        this.countThirdPlaces = countThirdPlaces;
    }

    public boolean isGreater(CountOfMedalsInCountry count) {
        if (countFirstPlaces > count.countFirstPlaces) {
            return true;
        }
        if (countFirstPlaces < count.countFirstPlaces) {
            return false;
        }
        if (countSecondPlaces > count.countSecondPlaces) {
            return true;
        }
        if (countSecondPlaces < count.countSecondPlaces) {
            return false;
        }
        if (countThirdPlaces > count.countThirdPlaces) {
            return true;
        }
        return false;
    }
}
